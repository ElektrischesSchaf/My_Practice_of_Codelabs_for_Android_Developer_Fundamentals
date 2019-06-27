/*
 * Copyright 2018, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.SimpleCalc;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static java.lang.Double.POSITIVE_INFINITY;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
@RunWith(JUnit4.class)
@SmallTest
public class CalculatorTest {

    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     */
    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }


    /**
     * A test with positive integer operands.
     */
    @Test
    public void powTwoPositive()
    {
        double result=mCalculator.pow(2d,3d);
            assertThat(result,is(equalTo(8d)));
    }

    /**
     * A test with a negative integer as the first operand.
     */
    @Test
    public void powFirstNegative()
    {
        double result=mCalculator.pow(-2d,3d);
        assertThat(result,is(equalTo(-8d)));
       // fail("powFirstNegative() hasn't been implemented.");
    }

    /**
     * A test with a negative integer as the second operand.
     */
    @Test
    public void powSecondNegative() {
        double result=mCalculator.pow(2d,-3d);
        assertThat(result,is(equalTo(0.125d)));
       // fail("powSecondNegative() hasn't been implemented.");
    }

    /**
     * A test with 0 as the first operand and a positive integer as the second operand.
     */
    @Test
    public void powFirstZeroSecondPositive() {
        double result=mCalculator.pow(0d,3d);
        assertThat(result,is(equalTo(0d)));
       // fail("powFirstZeroSecondPositive() hasn't been implemented.");
    }

    /**
     * A test with 0 as the second operand.
     */
    @Test
    public void powSecondZero() {
        double result=mCalculator.pow(2d,0d);
        assertThat(result,is(equalTo(1d)));

        //fail("powSecondZero() hasn't been implemented.");
    }

    /**
     * A test with 0 as the first operand and -1 as the second operand. (Hint: consult the documentation for Double.POSITIVE_INFINITY.)
     */
    @Test
    public void powFirstZeroSecondMinus1() {
        double result=mCalculator.pow(0d,-1d);
        assertThat(result,is(equalTo(Double.POSITIVE_INFINITY)));
     //   fail("powFirstZeroSecondMinus1() hasn't been implemented.");
    }

    /**
     * A test with -0 as the first operand and any negative number as the second operand.
     */
    @Test
    public void powFirstMinusZeroSecondNegative() {
        double result=mCalculator.pow(-0d,-1d);
        assertThat(result,is(equalTo(Double.NEGATIVE_INFINITY)));
        //fail("powFirstMinusZeroSecondNegative() hasn't been implemented.");
    }

}