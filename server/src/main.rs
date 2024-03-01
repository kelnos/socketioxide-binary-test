use axum::Router;
use serde_json::Value;
use socketioxide::{extract::{Bin, Data, SocketRef}, SocketIo};
use tokio::net::TcpListener;

#[tokio::main]
async fn main() {
    let (layer, io) = SocketIo::new_layer(); 
    let app = Router::new()
        .layer(layer);

    io.ns("/", |socket: SocketRef| async move {
        println!("Client connected");
        socket.on("test", |Data(_data): Data<Value>, Bin(bin)| async move {
            println!("Got binary data: {:?}", bin[0]);
        });
    });

    let listener = TcpListener::bind("127.0.0.1:8987").await.unwrap();
    axum::serve(listener, app).await.unwrap();
}
