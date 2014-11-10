package player;

/**
 * Duration specifies the length that a note, chord, or rest should last. 
 * The Duration for each note, chord, or rest depends on default duration in the key signature.
 */
public class Duration {
    
    public int numerator;
    public int denominator;
    
    /**
     * Determines duration from a string.
     *     This constructor method is primarily for use by the listener, which will 
     *     interpret the String following the note letter.
     * @param multFactor: String describing the duration 
     */

    public Duration(String multFactor) {
    	//if the string does not contain a slash and it is not empty
        if (!multFactor.contains("/") && !multFactor.isEmpty()) { 
            //e.g. A or A3
            int factor = Integer.parseInt(multFactor);
            if (factor == 1) { //e.g. A
                this.numerator = 1;
                this.denominator = 1;
            } else { //e.g. A3
                this.numerator = factor;
                this.denominator = 1;
            }
        } else { //if the string does contain a slash
            int slashPosition = multFactor.indexOf("/"); //determine slash position
            int multFactorLength = multFactor.length(); 
            if (slashPosition > 0) {
                String[] split = multFactor.split("/");
                if (multFactor.length() >= 3) { //e.g. A3/2 or A4/12
                    this.numerator = Integer.parseInt(split[0]);
                    this.denominator = Integer.parseInt(split[1]);
            	} else { //e.g. A3/
            		this.numerator = Integer.parseInt(split[0]);
            		this.denominator = 2;
            	}
            } else if (multFactor.length() == 1) { //e.g. A/
                this.numerator = 1;
                this.denominator = 2;
            } else { 
                if (slashPosition == 0) { //e.g. A/12
                	String[] split = multFactor.split("/");
                    this.numerator = 1;
                    this.denominator = Integer.parseInt(split[1]);

                }
            }
        }
        if(this.denominator==0) {
            System.out.println("ERROR: denominator is 0");
            System.out.println("Input string was BEGIN"+multFactor+"END");
        }
    }
    
    /**
     * Sets duration according to two integers that represent a fraction
     * @param numerator: numerator of fraction
     * @param denominator: denominator of fraction
     */
    public Duration(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
    /**
     * @param object to be compared with the duration object
     * @returns boolean saying whether the two objects are equal
     */
    public boolean equals(Object _that){
    	if (!(_that instanceof Duration)) {
                return false;
            }
        Duration that = (Duration) _that;
        return (this.toDouble() == that.toDouble());
    }

    /**
     * @return String representation of Duration object
     */
    public String toString(){
    	return Integer.toString(this.numerator) + '/' + Integer.toString(this.denominator);
    }
	/**
	 * @return a double value representing the duration (useful for giving tuplets their desired length)
	 */
    public double toDouble() {
        return ((double)this.numerator)/this.denominator;
    }

    /**
     * 
     * @return a deep copy of the Duration object
     */
    public Duration getDeepCopy() {
        return new Duration(numerator, denominator);
    }


}

