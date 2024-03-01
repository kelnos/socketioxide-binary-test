run-server:
	cd server && cargo run

run-client:
	cd client && mvn exec:java -Dexec.mainClass=org.spurint.socketioxidetest.Main
