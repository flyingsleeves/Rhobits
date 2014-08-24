all: Network Layer NeuronAndConnection

clean:
	rm ./bin/rhobits/*.class

Network: Layer
	javac -cp ./bin/ ./src/rhobits/Network.java -d ./bin/

Layer: NeuronAndConnection
	javac -cp ./bin/ ./src/rhobits/Layer.java -d ./bin/

NeuronAndConnection:
	javac -cp ./bin/ ./src/rhobits/Neuron.java ./src/rhobits/Connection.java -d ./bin/
