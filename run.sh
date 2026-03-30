MOTIVE=""
PORT=8000
CURR_DIR="$( cd -- "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"

# If no motive is provided, default to start
if [ -z "$1" ]; then
    MOTIVE="start"
else
    MOTIVE="$1"
fi

echo "Current: $CURR_DIR"

if [ ! -d "$CURR_DIR/logs" ]; then
    mkdir $CURR_DIR/logs
fi

function monitor_process {
    PID=$1
    while true; do
        if ! kill -0 "$PID" 2>/dev/null; then
            echo "Startup failed :("
            kill -15 $$
            exit 1
        fi
        sleep 1
    done
}

function stop {
    # pkill -f "java -jar target/randoms-1.0-SNAPSHOT.jar server config.yml"
    PID=$(lsof -i :$PORT -sTCP:LISTEN -t)
    if [ -n "$PID" ]; then
        kill -15 "$PID"
        if [ $? -ne 0 ]; then
            echo "Failed to stop server"
            return 1
        fi
        echo "Server stopped successfully on port $PORT"
        return 0
    fi
    echo "Server not running on port $PORT"
    return 0
}

function start {
    PID=$(lsof -i :$PORT -sTCP:LISTEN -t)
    if [ -n "$PID" ]; then
        echo "Server already running on port $PORT"
        return 1
    fi
    mvn clean install -DskipTests > $CURR_DIR/logs/mvn.log 2>&1
    if [ $? -ne 0 ]; then
        echo "Failed to build application"
        return 1
    fi
    java -jar $CURR_DIR/target/randoms-1.0-SNAPSHOT.jar server $CURR_DIR/config.yml > $CURR_DIR/logs/app.log 2>&1 &
    STATUS=$!
    echo "Waiting for server to listen on port $PORT..."
    monitor_process $STATUS &
    MONITORING_PID=$!
    # check if started by lsof
    while ! lsof -i :$PORT -sTCP:LISTEN -t >/dev/null; do
        sleep 1
    done
    kill -15 $MONITORING_PID
    if [ $? -ne 0 ]; then
        echo "Failed to start server"
        return 1
    else
        echo "Server started successfully on port $PORT"
    fi
    return $STATUS
}

if [ "$MOTIVE" = "stop" ]; then
    stop
    exit 0
elif [ "$MOTIVE" = "start" ]; then
    start
    exit $?
elif [ "$MOTIVE" = "restart" ]; then
    stop
    start
    exit $?
else
    echo "Invalid motive: $MOTIVE"
    exit 1
fi
