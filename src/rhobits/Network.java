package rhobits;

import java.util.ArrayList;

public class Network
{
    // Class Variables
    private static boolean debug = true;
    private static boolean neuronDebug = false;
    private Layer inputLayer;
    private Layer outputLayer;
    private ArrayList<Layer> layerList = new ArrayList<Layer>();
    private double globalError = 0;
    private double errorSetSize = 0;
    private ArrayList<Double> outputList = new ArrayList<Double>();

    // Constructors
    public Network() { }

    // Methods
    public static void main(String args[])
    {
        final Network network = new Network();
        if (debug) network.test();
    }

    private void test()
    {
        System.out.println("____________");
        System.out.println("Network Test");
        System.out.println("____________");

        this.build3LayerNet(2, 2, 1);

        this.randomizeAll();

        this.printLayers();

        double[] input1 = {0, 0};
        double[] input2 = {1, 0};
        double[] input3 = {0, 1};
        double[] input4 = {1, 1};

        this.present(input1);
        this.present(input2);
        this.present(input3);
        this.present(input4);
    }

    private void addLayer(int numNeurons)
    {
        Layer l = new Layer(numNeurons);
        this.layerList.add(l);

        if (this.layerList.size() == 1)
            this.inputLayer = l;

        this.outputLayer = l;
    }

    private void build3LayerNet(int sizeL1, int sizeL2, int sizeL3)
    {
        this.layerList.clear();

        Layer l1 = new Layer(sizeL1);
        Layer l2 = new Layer(sizeL2);
        Layer l3 = new Layer(sizeL3);

        this.layerList.add(l1);
        this.layerList.add(l2);
        this.layerList.add(l3);
        this.inputLayer = l1;
        this.outputLayer = l3;

        l1.connectToLayer(l2);
        l2.connectToLayer(l3);
    }

    // Randomize all weights and thresholds in the network
    private void randomizeAll()
    {
        for (Layer l : this.layerList)
            l.randomizeAll();
    }

    private void present(double[] pattern)
    {
        if (pattern.length != inputLayer.getSize())
        {
            System.out.println("Pattern size != number of input neurons");
            return;
        }

        System.out.print("Presenting [ ");
        for (int i = 0; i < pattern.length; i++)
        {
            System.out.print(pattern[i] + " ");
            inputLayer.getNeuronList().get(i).addToInput(pattern[i]);
        }
        System.out.println("]");

        for (Layer l : this.layerList)
        {
            l.fire();
        }

        //this.outputLayer.printNeuronOutputs();
        this.outputList.clear();
        for (Neuron n : this.outputLayer.getNeuronList())
            this.outputList.add(n.getOutput());

        for (Double d : this.outputList)
            System.out.printf("(%.1f) ", d);

        System.out.println();
    }

    private void train(double[] pattern, double[] ideal)
    {
        if (pattern.length != inputLayer.getSize())
        {
            System.out.println("Pattern size != number of input neurons");
            return;
        }

        System.out.print("Presenting [ ");
        for (int i = 0; i < pattern.length; i++)
        {
            System.out.print(pattern[i] + " ");
            inputLayer.getNeuronList().get(i).addToInput(pattern[i]);
        }
        System.out.println("]");

        for (Layer l : this.layerList)
        {
            l.fire();
        }

        //this.outputLayer.printNeuronOutputs();
        this.outputList.clear();
        for (Neuron n : this.outputLayer.getNeuronList())
            this.outputList.add(n.getOutput());

        for (Double d : this.outputList)
            System.out.printf("(%.1f) ", d);

        System.out.println();
    }

    private double calculateRMS() 
    {
        return Math.sqrt(this.globalError / this.errorSetSize);
    }

    private void accumulateError(ArrayList<Double> output, ArrayList<Double> ideal)
    {
        for (int i = 0; i < output.size(); i++)
        {
            this.globalError += Math.pow(output.get(i) - ideal.get(i), 2);
            this.errorSetSize += output.size();    
        }
    }

    private void resetError()
    {
        this.globalError = 0;
        this.errorSetSize = 0;
    }

    // Printing Methods
    private void printLayers()
    {
        System.out.println();
        for (Layer l : this.layerList)
            l.printNeurons();
    }
}
