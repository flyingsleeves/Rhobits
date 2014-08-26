package rhobits;

public class NeuralMath
{
	private NeuralMath() {}

	public double derivativeTANH(double d)
    {
        return (1.0 - Math.pow(Math.tanh(d), 2.0));
    }
}