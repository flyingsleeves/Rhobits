package rhobits;

import java.util.ArrayList;

public class Connection
{
    // Class Variables
    private Neuron fromNeuron;
    private Neuron toNeuron;
    private double weight = 1;

    // Constructors
    public Connection(Neuron from, Neuron to)
    {
        this.fromNeuron = from;
        this.toNeuron = to;
    }

    public Connection(Neuron from, Neuron to, double weight)
    {
        this.fromNeuron = from;
        this.toNeuron = to;
        this.weight = weight;
    }

    // Methods
    public Neuron getFromNeuron()
    {
        return this.fromNeuron;
    }

    public Neuron getToNeuron()
    {
        return this.toNeuron;
    }

    public double getWeight()
    {
        return this.weight;
    }

    public void setFromNeuron(Neuron from)
    {
        this.fromNeuron = from;
    }

    public void setToNeuron(Neuron to)
    {
        this.toNeuron = to;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }
}