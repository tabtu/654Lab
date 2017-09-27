package uow.acc.tab.analysis;

/**
 * Created by Tab on 2017-09-12.
 */
import java.util.Random;

//This includes additional code not in the text that keeps
//track of the starting and ending points of best sequence

public final class MaxSumTest
{
    static private int seqStart = 0;
    static private int seqEnd = -1;

    /**
     * Cubic maximum contiguous subsequence sum algorithm.
     * seqStart and seqEnd represent the actual best sequence.
     */
    public static int maxSubSum1( int [ ] a )
    {
        int maxSum = 0;

        for( int i = 0; i < a.length; i++ )
            for( int j = i; j < a.length; j++ )
            {
                int thisSum = 0;

                for( int k = i; k <= j; k++ )
                    thisSum += a[ k ];

                if( thisSum > maxSum )
                {
                    maxSum   = thisSum;
                    seqStart = i;
                    seqEnd   = j;
                }
            }

        return maxSum;
    }

    /**
     * Quadratic maximum contiguous subsequence sum algorithm.
     * seqStart and seqEnd represent the actual best sequence.
     */
    public static int maxSubSum2( int [ ] a )
    {
        int maxSum = 0;

        for( int i = 0; i < a.length; i++ )
        {
            int thisSum = 0;
            for( int j = i; j < a.length; j++ )
            {
                thisSum += a[ j ];

                if( thisSum > maxSum )
                {
                    maxSum = thisSum;
                    seqStart = i;
                    seqEnd   = j;
                }
            }
        }

        return maxSum;
    }

    /**
     * Linear-time maximum contiguous subsequence sum algorithm.
     * seqStart and seqEnd represent the actual best sequence.
     */
    public static int maxSubSum4( int [ ] a )
    {
        int maxSum = 0;
        int thisSum = 0;

        for( int i = 0, j = 0; j < a.length; j++ )
        {
            thisSum += a[ j ];

            if( thisSum > maxSum )
            {
                maxSum = thisSum;
                seqStart = i;
                seqEnd   = j;
            }
            else if( thisSum < 0 )
            {
                i = j + 1;
                thisSum = 0;
            }
        }

        return maxSum;
    }


    /**
     * Recursive maximum contiguous subsequence sum algorithm.
     * Finds maximum sum in subarray spanning a[left..right].
     * Does not attempt to maintain actual best sequence.
     */
    private static int maxSumRec( int [ ] a, int left, int right )
    {
        int maxLeftBorderSum = 0, maxRightBorderSum = 0;
        int leftBorderSum = 0, rightBorderSum = 0;
        int center = ( left + right ) / 2;

        if( left == right )  // Base case
            return a[ left ] > 0 ? a[ left ] : 0;

        int maxLeftSum  = maxSumRec( a, left, center );
        int maxRightSum = maxSumRec( a, center + 1, right );

        for( int i = center; i >= left; i-- )
        {
            leftBorderSum += a[ i ];
            if( leftBorderSum > maxLeftBorderSum )
                maxLeftBorderSum = leftBorderSum;
        }

        for( int i = center + 1; i <= right; i++ )
        {
            rightBorderSum += a[ i ];
            if( rightBorderSum > maxRightBorderSum )
                maxRightBorderSum = rightBorderSum;
        }

        return max3( maxLeftSum, maxRightSum,
                maxLeftBorderSum + maxRightBorderSum );
    }

    /**
     * Return maximum of three integers.
     */
    private static int max3( int a, int b, int c )
    {
        return a > b ? a > c ? a : c : b > c ? b : c;
    }

    /**
     * Driver for divide-and-conquer maximum contiguous
     * subsequence sum algorithm.
     */
    public static int maxSubSum3( int [ ] a )
    {
        return a.length > 0 ? maxSumRec( a, 0, a.length - 1 ) : 0;
    }

    public static void getTimingInfo( int n, int alg )
    {
        int [] test = new int[ n ];

        long startTime = System.currentTimeMillis( );;
        long totalTime = 0;

        int i;
        for( i = 0; totalTime < 4000; i++ )
        {
            for( int j = 0; j < test.length; j++ )
                test[ j ] = rand.nextInt( 100 ) - 50;

            switch( alg )
            {
                case 1:
                    maxSubSum1( test );
                    break;
                case 2:
                    maxSubSum2( test );
                    break;
                case 3:
                    maxSubSum3( test );
                    break;
                case 4:
                    maxSubSum4( test );
                    break;
            }

            totalTime = System.currentTimeMillis( ) - startTime;
        }

        System.out.print( String.format( "\t%12.6f", ( totalTime * 1000 / i ) / (double) 1000000 ) );
    }

    private static Random rand = new Random( );

    /**
     * Simple A4_6 program.
     */
    public static void main( String [ ] args )
    {
        System.out.println("Max sum is,start at,ent at,maxSubSum1,maxSubSum2,maxSubSum3,maxSubSum4;");

        for (int i = 0; i < 5; i++) {

            Random aR = new Random();
            int a[] = aR.ints(2000, -100, 100).toArray();

            int maxSum;
            long start, end;
            String tmp = "";

            start = System.currentTimeMillis();
            maxSum = maxSubSum1(a);
            end = System.currentTimeMillis();
            tmp += maxSum + "," + seqStart + "," + seqEnd + ",";
            tmp += (end - start) + ",";
            //System.out.println( "Max sum is " + maxSum + "; it goes" + " from " + seqStart + " to " + seqEnd );
            //System.out.println( ":maxSubSum1 Time used: " + ( end - start ));

            start = System.currentTimeMillis();
            maxSum = maxSubSum2(a);
            end = System.currentTimeMillis();
            tmp += (end - start) + ",";
            //System.out.println( "Max sum is " + maxSum + "; it goes" + " from " + seqStart + " to " + seqEnd );
            //System.out.println( ":maxSubSum2 Time used: " + ( end - start ));

            start = System.currentTimeMillis();
            maxSum = maxSubSum3(a);
            end = System.currentTimeMillis();
            tmp += (end - start) + ",";
            //System.out.println( "Max sum is " + maxSum + "; it goes" + " from " + seqStart + " to " + seqEnd );
            //System.out.println( ":maxSubSum3 Time used: " + ( end - start ));

            start = System.currentTimeMillis();
            maxSum = maxSubSum4(a);
            end = System.currentTimeMillis();
            tmp += (end - start) + ";";
            //System.out.println( "Max sum is " + maxSum + "; it goes" + " from " + seqStart + " to " + seqEnd );
            //System.out.println( ":maxSubSum4 Time used: " + ( end - start ));

            System.out.println(tmp);
        }
    }
}