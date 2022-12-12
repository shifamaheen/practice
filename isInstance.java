
/*
The instanceof operator and isInstance() method both are used 
for checking the class of the object.

The main difference comes when we want to check the class of objects dynamically 
then isInstance() method will work. There is no way we can do this by instanceof operator.

The isInstance method is equivalent to instanceof operator. 
The method is used in case of objects are created at runtime using reflection.
General practice says if the type is to be checked at runtime 
then use the isInstance method otherwise instanceof operator can be used.

The instanceof operator and isInstance() method both return a boolean value.
isInstance() method is a method of class Class in java while instanceof is an operator.
*/
class test
{
	public static void main(String[] args) throws ClassNotFoundException
	{
		Integer i = 5;
        System.out.println(i instanceof Integer);

        // Below line causes compile time error
		// Incompatible types test cannot be converted to String
		test t = new test();
		// System.out.println(t instanceof String);

		// returns the Class object for this class
		Class myClass = Class.forName("test");

		System.out.println("Class represented by myClass: "	+ myClass.toString());

		// get the Class instance using forName() method
		Class c = Class.forName("java.lang.String");

		System.out.println("Class represented by c: " + c.toString());

		// Check if object c is compatible using isInstance() method
		System.out.println("Is c compatible: "	+ myClass.isInstance(c));
	}
}
