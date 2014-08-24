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
        System.out.println("Network Test");

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

    private void build3LayerNet(int sizeL1, int sizeL2, int sizeL3)
    {
        this.layerList = new ArrayList<Layer>();

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

        this.outputLayer.printNeuronOutputs();
        System.out.println();
    }

/*    private double calculateError(double actual[][], double ideal[][])
    {
        double globalError = 0;
        double setSize = 0;

        for (int i = 0; i < actual.length; i++)
        {
            for (int j = 0; j < actual[i].length; j++)
            {
                double delta = ideal[j] - actual[j];
                globalError += delta * delta;
                setSize += actual.length;
            }
        }
    }*/

    private void reset()
    {
        for (Layer l : this.layerList)
            l.reset();
    }

    private void randomizeAll()
    {
        for (Layer l : this.layerList)
            l.randomizeAll();
    }

    private void printLayers()
    {
        System.out.println();
        for (Layer l : this.layerList)
            l.printNeurons();
    }
}
