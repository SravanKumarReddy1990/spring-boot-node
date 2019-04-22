<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>
    
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
    WebSocketTest()
    function WebSocketTest() {
            
            if ("WebSocket" in window) {
              // alert("WebSocket is supported by your Browser!");
               
               // Let us open a web socket
               var ws = new WebSocket("ws://18.222.87.33:8080/websocket/chat/sravan");
				
               ws.onopen = function() {
                  console.log("Connected")
                  // Web Socket is connected, send data using send()
                  ws.send('{"from":"sravan","to":"sravan1","content":"sdmv n","status":"jsn vjsd"}');
                  //alert("Message is sent...");
               };
				
               ws.onmessage = function (evt) { 
                  var received_msg = evt.data;
var received_msg_json=JSON.parse(received_msg);
	console.log(received_msg_json);
                  //alert("Message is received..."+received_msg_json.auth);
               };
				
               ws.onclose = function() { 
                  
                  // websocket is closed.
                  //alert("Connection is closed..."); 
               };
            } else {
              
               // The browser doesn't support WebSocket
               alert("WebSocket NOT supported by your Browser!");
            }
         }
    </script>
</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
    enabled. Please enable
    Javascript and reload this page!</h2></noscript>
<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="connect">WebSocket connection:</label>
                    <input id="login" type="text" />
                    <button id="connect" class="btn btn-default" type="submit">Connect</button>
                    <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect
                    </button>
                </div>
            </form>
        </div>
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="name">Receiver name ?</label>
                    <input type="text" id="name" class="form-control" placeholder="Your name here...">
                </div>
                <button id="send" class="btn btn-default" type="submit">Send</button>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="conversation" class="table table-striped">
                <thead>
                <tr>
                    <th>Greetings</th>
                </tr>
                </thead>
                <tbody id="greetings">
                </tbody>
            </table>
        </div>
    </div>
    </form>
</div>
</body>
</html>
