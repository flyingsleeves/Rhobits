package rhobits;

import java.util.ArrayList;

public class Layer
{
    // Class Variables
    private static int totalLayers = 0;
    private int id = 0;
    private ArrayList<Neuron> neuronList = new ArrayList<Neuron>();
    private int numNeurons;

    // Constructors
    public Layer()
    {
        totalLayers++;
        this.id = totalLayers;
    }

    public Layer(int neurons)
    {
        totalLayers++;
        this.id = totalLayers;
        for (int i = 0; i < neurons; i++)
            this.addNeuron(new Neuron());
    }

    // Methods
    public int getId()
    {
        return this.id;
    }

    public int getSize()
    {
        return this.neuronList.size();
    }

    public ArrayList<Neuron> getNeuronList()
    {
        return this.neuronList;
    }

    public void addNeuron(Neuron n)
    {
        this.neuronList.add(n);
        this.numNeurons++;
        //if (debug) System.out.println("Adding neuron id " + n.getId() + " to the list.");
    }

    public void connectToLayer(Layer toLayer)
    {
        for (Neuron n1 : this.neuronList)
        {
            for (Neuron n2 : toLayer.getNeuronList())
                n1.addOutConnection(n2);
        }
    }

    public void fire()
    {
        for (Neuron n : this.neuronList)
            n.fire();
    }

    public void reset()
    {
        for (Neuron n : this.neuronList)
            n.reset();
    }

    public void randomizeAll()
    {
        for (Neuron n : this.neuronList)
            n.randomizeAll();
    }

    public void printNeurons()
    {
        System.out.println("---Layer " + this.id + "---");
        for (Neuron n : this.neuronList)
        {
            System.out.print("Neuron " + n.getId() + ": t = ");
            System.out.printf("%.2f ->", n.getThreshold());
            n.printOutConnections();
            System.out.println();
        }
        System.out.println("-------------");
        System.out.println();
    }

    public void printNeuronMap()
    {
        System.out.print("L" + this.id + ": <");
        for (Neuron n : this.neuronList)
        {
            System.out.print(" (N" + n.getId() + ", " + n.getThreshold() + ")");
        }
        System.out.println(" >");
    }

    public void printNeuronThresholds()
    {
        System.out.print("Thresholds: < ");
        for (Neuron n : this.neuronList)
        {
            System.out.print(n.getThreshold() + " ");
        }
        System.out.println(">");
    }

    public void printNeuronOutputs()
    {
        System.out.print("Output [ ");
        for (Neuron n : this.neuronList)
        {
            System.out.printf("%.2f ", n.getOutput());
        }
        System.out.println("]");
    }
}