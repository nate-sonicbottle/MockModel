package com.sonicbottle.mockModel;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Starting");
    	
        MockModelExample example = new MockModelExample();
        System.out.println(example.getHello());
        System.out.println(example.getBob());
    	
//    	System.out.println(DataFactory.getBoolean());
//    	System.out.println(DataFactory.getBoolean());
        
        System.out.println("Ending");
    }
}
