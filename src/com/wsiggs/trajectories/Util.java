package com.wsiggs.trajectories;

/**
 * @author Siggy
 *         $
 */
public class Util
{
    /**
     * simple factorial calculation
     *
     * @param a
     * @return
     */
    public static int factorial(int a)
    {
        int ret = 1;

        for(int i = a; i > 0; i--)
        {
            ret *= i;
        }
        return ret;
    }

    /**
     * Returns the binomial coefficient given two integers, n and k
     * "N choose K", or How many ways can K elements be chosen from N elements
     * Ex: How many different ways can 3 cards be chosen from a deck of 52?
     *
     * @param n
     * @param k
     * @return
     */
    public static int binoCoef(int n, int k)
    {
        return (factorial(n)/(factorial(k)*factorial(n-k)));
    }

    /**
     * Returns the Bernstein polynomial value given n, v, and s, which is used
     * as the coefficient for the reference points
     * The higher s, the more influence the reference point has on the curve.
     *
     * @param n Degree of the curve
     * @param v Index of reference point
     * @param s Percent completion or distance along curve
     * @return
     */
    public static double bernstein(int n, int v, double s)
    {
        return binoCoef(n, v)*Math.pow(s, v)*Math.pow((1-s), n-v);
    }


    /**
     *
     * @param refTable  Reference table that conversions will be based off of
     * @param val       Value we need to convert
     * @param rot       If we are rotating, values can also be negative
     * @return
     */
    public static double lookUp(double[] refTable, double val, boolean rot)
    {
        int upper = 0, lower = 0;
        boolean negative = false;

        if(rot && val < 0.0)
        {
            negative = true;
            val = Math.abs(val);
        }


        for(int i = 0; i < refTable.length-1; i++)
        {
            if(refTable[i+1] >= val && refTable[i] <= val)
            {
                lower = i;
                upper = i+1;
                break;
            }
        }

        double ret = (lower/10.0)+((val-refTable[lower])/(refTable[upper]-refTable[lower]))/10.0;
        if(negative)
            ret *= -1.0;

        return ret;
    }

}
