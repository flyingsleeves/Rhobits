package rhobits;

import java.util.ArrayList;
import java.text.*;

public class Neuron
{
    // Class Variables
    private static int totalNeurons = 0;
    private int id;
    private double threshold = 0;
    private double input = 0;
    private double output = 0;
    private ArrayList<Connection> inConnectionList = new ArrayList<Connection>();
    private ArrayList<Connection> outConnectionList = new ArrayList<Connection>();

    // Constructors
    public Neuron()
    {
        totalNeurons++;
        this.id = totalNeurons;
        //if (debug) System.out.println("Creating Neuron: id = " + this.id + ", threshold = " + this.threshold);
    }

    public Neuron(double threshold)
    {
        totalNeurons++;
        this.id = totalNeurons;
        this.threshold = threshold;
        //if (debug) System.out.println("Creating Neuron: id = " + this.id + ", threshold = " + this.threshold);
    }

    // Methods
    public int getId()
    { 
        return this.id;
    }

    public double getThreshold()
    {
        return this.threshold;
    }

    public double getOutput()
    {
        return this.output;
    }

    public void addOutConnection(Neuron out)
    {
        Connection c = new Connection(this, out);
        this.outConnectionList.add(c);
    }

    public void addInConnection(Neuron in)
    {
        Connection c = new Connection(in, this);
        this.inConnectionList.add(c);
    }

    public void addToInput(double input)
    {
        this.input += input;
    }

    public void fire()
    {
        this.output = 0;

        if (this.input > this.threshold)// && this.output != 0)
        {
            //if (neuronDebug) System.out.println("Neuron " + this.id + " Firing: (" + this.input + " > " + this.threshold + ")");
            
            this.computeOutput(this.input);

            for (Connection c : this.outConnectionList)
            {
                c.getToNeuron().addToInput(this.output * c.getWeight());
                //if (neuronDebug) System.out.println("  " + (this.output * c.getWeight()) + " -> N" + c.getToNeuron().getId());
            }
        }
        else
        {
            this.output = 0;
        }
        this.input = 0;
    }

    private double derivativeTanh(double d)
    {
        return (1.0 - Math.pow(Math.tanh(d), 2.0));
    }

    private void computeOutput(double i)
    {
        this.output = Math.tanh(i);
    }

    public void randomizeAll()
    {
        this.threshold = (Math.random()*2) - 1;
        for (Connection c : this.outConnectionList)
        {
            c.setWeight((Math.random()*2) - 1);
        }
    }

    public void reset()
    {
        this.input = 0;
        this.output = 0;
    }

    public void printOutConnections()
    {
        for (Connection c : this.outConnectionList)
        {
            System.out.print(" (N" + c.getToNeuron().getId() + ", ");
            System.out.format("%.2f)", c.getWeight());
        }
    }
}