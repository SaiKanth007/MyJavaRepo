package src.theory;

//when the implementing interfaces have same default methods, then the implementing
//class has to override the default method so as to dictate the behvaiour
public class JavaDiamondProblem implements Interface1, Interface2 {
	public static void main(String[] args) {
		new JavaDiamondProblem().foo();
	}

	@Override
	public void foo() {
		// TODO Auto-generated method stub
		Interface1.super.foo(); // Interface2.super.foo()
	}
}

interface Interface1 {
	default public void foo() {
		System.out.println("Interface1's foo");
	}
}

interface Interface2 {
	default public void foo() {
		System.out.println("Interface2's foo");
	}
}

// base class wins over interface
class BaseClass {
	public void foo() {
		System.out.println("BaseClass's foo");
	}
}

interface BaseInterface {
	default public void foo() {
		System.out.println("BaseInterface's foo");
	}
}

// if there is a default method in interface same as the one in the class that is being extended,
// the class wins
class Diamond extends BaseClass implements BaseInterface {
	public static void main(String[] args) {
		new Diamond().foo();
	}
}
