# Date converters

This utility can be used in any of your software projects in Java and is made available on Maven central. 

Save yourself from writing all the mundane boiler plate code for executing date conversions by using this library.

This utility converts any given date-type object to any other date-type and returns the converted date. A single line of code to invoke the utility is all what is required. This utility is available on the maven central repo under the MIT license. 

This converter can convert dates between various types mutually among the APIs mentioned here –
1.	Old-school JDK date API
2.	Java8 time API
3.	Joda date API.
4.	Date strings.

The single line of code to invoke the utility is shown here. 

		- DateConverterFacade.convert(<date object or date-string>, <target-date-class-type>); 

To invoke this utility, only 2 method parameters should be passed which are the date object and the target date-type.  The utility will do the necessary conversion and return the converted date object. 

The first parameter is the source date-object or the date-string. And the 2nd parameter is the date-type to which you want it to be converted to.

• And when it comes to converting date-strings to a date-type in Java, usually the date-format is also required to be specified. But however, there is a very useful dateparser library contributed by a github user "Sisyphsu Lin" which can determine the date-format string from a given date-string. For converting date-strings to a couple of Date types, the Date-converter utility internally uses this library eliminating the need to specify a date-format string altogether.

• The link to the date-converter utility on maven central is - https://mvnrepository.com/artifact/org.otclfoundation/dateconverters 
