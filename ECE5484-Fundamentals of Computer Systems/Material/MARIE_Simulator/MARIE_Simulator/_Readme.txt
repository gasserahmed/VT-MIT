On a Windows machine, the MARIE machine simulator and accompanying datapath simulator may be run directly from their respective JAR files, assuming that you have Sun's Java Virtual Machine installed. Just copy the JAR file to the folder of your choice and double click on it. 

If you would rather run the simulators fom a command prompt, type:

java -jar MarieSim.jar // Run the main simulator
java -jar MarieDP1.jar // Run the datapath simulator

Be sure that your classpath is set correctly. See MarieGuide.pdf for more information.
 
If want to run the simulators from a command prompt and look at the source code, use the following commands to unpack the JAR once you have copied it to your computer:

jar xvf MarieSim.jar // Class files
jar xvf MarieSrc.jar  // Source code (Includes the datapath simulator.)
java MarieSim1  // Run the main simulator
java MarieDP1 // Run the datapath simulator

Note: This step is also necessary if there is a problem with your Windows file associations. 

See MarieGuide.pdf for more information.

Some example code from the book has been included for your convenience.
