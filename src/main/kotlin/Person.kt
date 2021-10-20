// ===============================================================================================================================

// 1.   Creating a class
// with the new IntelliJ IDE go to: src/main/kotlin -> new Kotlin File/Class - select class
// primary constructor parameters goes in the class header
// var are read-write while val are read-only
// the primary constructor can be ommitted if has no visibilty
// Primary constructor omitted if no annotations or visibility modifiers - `class Person(var firstName: String, val lastName: String)`
// firstName is mutable while lastName is immutable


class Person constructor(var firstName: String, val lastName: String)

val f = Person("Jay", "Doe")
// :TODO: Pro tip
// If you are writing OOP code use `var` so you can easily mutate them,
// If you prefer FP, consider Using Kotlin data class

// ===============================================================================================================================

// 3. Constructor parameters visibility
// class constructor params are defined in the class header
// `var` means values are mutable -have both getters and setters (read-from and written-to) while val values are immutable (read-only)

// ===============================================================================================================================

// 4. Annotations and visibility modifiers
// If the class constructor has annotations or visibility modifiers, the constructor keyword is required
// example: modified goes before constructor keyword
// WTF TODO: is @Inject ????
// Kotlin visibility modifiers are `private`, `protected`, `internal`, `public`
// classes, objects, interfaces, constructors, functions and properties and their setters have visibility modifiers
// `internal` modifiers is visible anywhere inside the same module

// class Customer public @Inject constructor(name: String)

// ===============================================================================================================================

// 5. Import and Packages
// -they are very similar to Java  - but Kotlin support renaming of a class when it is imported
// other importable things in Kt are top-level functions, functions or properties declared object declaration and Enum constants
// collection of classes and methods are imported by default such as `kotlin`
// Put a package statement at the top of a file -> package foo.bar.baz
// to import a function -> import foo.bar.plus1 where plus1 is the function
// import statements in Kt
//      1. there is no `import static` - use regular import eg `import java.lang.Math.PI` usage: PI () or `import java.lang.Math.pow` usage: (pow(2.0, 2.0 ))
//      2. You can rename a class when you import it - import java.util.HashMap as JavaHashMap
//      3. the `import` is not restricted to only importing classes
// println() is actually `System.out.println` meaning that Kotlin imports a collection of packages by default eg
// kotlin.collections, kotlin.comparisons, kotlin.*, kotlin.ranges, kotlin.annotations, kotlin.texts, kotlin.io etc
// println() is defined in `kotlin.io` package hence automatically made available

// ===============================================================================================================================

// 6. Class Members
// classes contains constructors, constructor initializer blocks, functions, properties, Nested and Inner classes, Object declarations
//      6.1 constructors - only needed when class has annotations and visibility modifiers
class Person1(name: String, age: Int) // when no visibility modifiers or annotations are required
class Person2 @JvmOverloads constructor(name: String, age: Int) // when only annotation is passed
class Person3 @JvmOverloads private constructor(name: String, age: Int) // passing annotations and visibility modifiers
//      6.2 init block - primary initialization code goes inside `init block
        // functions similar to initialize block
        // Kotlin supports multiple init blocks
class Person4(name: String) {
    // code inside init block runs when an instance of a class is created
    // this prints when a new instance is created
    // init blocks have access to constructor params
    init {
        println("Person instance created")
        // init block accessing constructor args
        println("Hi $name")
    }
}
class Socket(var timeout: Int, var linger: Int) {
    init {
        println("Entered 'init'...")
        println("timeout = $timeout, linger = $linger")
    }
}
//      6.3 Methods (functions)
// Kt functions are correctly called method on OOP - to create a method fullname
// prefix with val or var to access constructor params in the class
class Person5(val firstName: String,  val lastName: String) {
    // defining a method inside a class
    fun fullName() = "$firstName $lastName"
    //  a method that accepts an argument
    fun addTopping(t: String) {
        toppings.add(t)
    }

    // 6.4 Properties (fields)
    // properties are fields
    val toppings = mutableListOf<String>() // fields defined as if variable assignment

    //  6.4 Nested classes
    // A nested class can't have access to parameter in the outer class
    // to use a nested class -> val foo =  Person5.Nested().foo()
    // NB: the weird syntax Person5.Nested().foo()
    // usage: Person5.Nested().foo()
    private val x = 1
    class Nested {
        // this won't have access to val x -  to access this use inner class (prefix with inner)
        fun foo() : Int {
            // println("From inner class and outer variable $x accessible") // x not available
            return 2
        }
    }


    // 6.5 Mark a nested class with `inner` to allow the class to have access to outer class variables
    // To call an inner class -> Person5().Inner().foo() - weird thing going on with inner class and nested class
    // usage: Person5().Inner().foo()
    private val y = 1
    inner class Inner {
        fun foo() {
            println("From inner class and outer variable $y accessible")
        }
    }

    // 6.6 Syntax difference between Nested class and Inner class
    // val foo = Outer.Nested().foo() // Nested class
    // for Inner class, you need to access the Inner class by first creating an instance of the Outer class with its all arguments
    // val foo = Outer().Inner().foo() // Inner class

}

// ===============================================================================================================================

// 7. Custom class fields Getters and Setters
// - Kt has properties (called fields in Java) - defined with `val` or `var`
// - val are read-only and var are read/write
// - Fields (properties are accessed directly by the name) - they are public by default but can be made private
// - You can create custom getters(accessor) and setters(mutator) methods for your properties with a special syntax
// - Getters and setters can use a 'backing field' named field

// 7.1 Kotlin field without getter and setter - declare such field as var
class Person6 {
    var initials: String = ""
    // this allows to do - using getter and setter for a simple property
    // val p = Person6()
    // p.initials // get (access) value
    // p.initials = "JN" // set the value

    // 7.2 Custom get/set methods
    // - define custom get() and set() methods just below the prop name to create custom getter and setter
    // - eg: make a log entry everytime someone access or updates the name attribute of the person
    // Keypoints:
    // - getters and setters can use the backing field named field getter:(return field) and setter:(field = passed_value)
    // - field is the way to refence the value you were referencing in this case `name` field
    var name: String = "<no name>"
    get() { // custom getter method
       println("OMG, someone accessed 'name'")
        return field // Backing field in Kt
    }
    set(s) { // custom setter method
        println("OMG, someone updated the value of 'name' to be $s")
        field = s // this is called backing field in Kt
    }
}

// ===============================================================================================================================

// 8. Constructor Default Values & Named Arguments
// - Kotlin has 2 feature borrowed from Scala: 1. default values for constructor params 2. use named arguments when calling constructor
// - to use class without default constructor params
// - class Socket1(var timeout: Int, var linger: Int) {
//      override def toString = "timeout: $timeout, linger: $linger"
// }
// 8.1 Default values in class constructors
// - to supply default values for timeout and linger - (DEFAULT VALUES)
// - with default values, you do not need to pass any values
// 8.2 Named arguments
// - Named arguments are used when creating new instances of a class
// - Given below Socket1 class: a new instance with a named argument will be
// - var s = Socket1(timeout = 2000, linger = 3000)
// - Named arguments make you code more readable especially in Code Reviews.
class Socket1(var timeout: Int = 2000, var linger: Int = 3000) {
    override fun toString(): String = "timeout: $timeout, linger: $linger"
}

// ===============================================================================================================================

// 9.0 Secondary class constructors in Kotlin
// Rule about Kotlin Secondary constructors
//      a. a class can have 0 or more secondary constructors
//      b. a secondary constructor must call the primary constructor, (either calling it directly or calling another secondary constructor that calls the primary constructor)
//      c. other constructors are called via `this` keyword
//      d. the @JvmOverloads annotations lets Kotlin classes that have default parameters values be created in Java code
// - Below is an example with primary constructor and two other auxiliary constructors
class Pizza constructor(
    // this denotes the primary constructor
    var crustSize: String,
    var crustType: String,
    var toppings: MutableList<String> = mutableListOf() // toppings has default list of empty
) {
    // this denotes secondary constructor (no-args
    // defines with constructor keyword - this calls primary constructor via `this`
    constructor() : this("SMALL", "THIN")

    // secondary constructor (2-args)
    constructor(
        crustSize: String,
        crustType: String
    ) : this(
        crustSize,
        crustType, mutableListOf<String>()
    )

    // overriding a function is like defining the same function
    // that is fun main() {} or fun toString() but you put override at the start of the fun keyword
    override fun toString(): String = "size: ${crustSize}, type: ${crustType}, toppings: ${toppings}"

    // with this class and its 3 constructors (primary and the 2 auxiliary constructors)
    // you can use this class in 3 ways
    // 1. Pass nothing and the second constructor will be called with crustSize: SMALL and crustType as THIN
    //      Pizza() -> second constructor called crustSize: SMALL and crustType as THIN,
    // 2. Pass crustSize and crustType eg LARGE and THICK & the 3rd constructor with 2 args will be called
    //      Pizza("Large", "THICK")
    // 3. Pass all arguments and the primary constructor will be called
    //     Pizza("MEDIUM", "REGULAR", mutableListOf("CHEESE",  "PEPPERONI"))


    // Default constructor values eliminates the need for secondary constructors like this:
    // class Pizza2 constructor(
    //    var crustSize: String = "SMALL",
    //    var crustType: String = "THIN",
    //    val toppings: MutableList<String> = mutableListOf()
    // ) {
    //     override fun toString(): String = "size: ${crustSize}, type: ${crustType}, toppings: ${toppings}"
    // }
    //
}

// 9.1 @JvmOverloads (Working with Java)
// - the @JvmOverloads annotation "Instruct the kotlin compiler to generate the overloads for this function that substitute default parameter values
// - Not used so much but used when generating multiple constructors when you want to work with Java code
// - TODO: HARD TO PICK THIS CONCEPT FOR ME

// ===============================================================================================================================

// 10. Open and final classes (Concept of fragile classes in Kotlin)
// - Kotlin classes and functions are `final` by default, this is due fragile base class problem
// - Mark a class as `open` to allow it to be extended (open to be extended)
// - To allow class functions and fields to be overriden, you must mark them as `open`

//      10. 1 Classes and functions are final by default

// - a. Classes and functions are not open (default)
//   class Parent { // class is final by default
//     fun name(): String = "base" // function is final by default
//  }
// creating a class that inherit from Parent, trying to override name wont work
//   class Child : Parent() { // this type is final, so it cannot be inherited
//       override fun name(): String = "Child" // name in 'Parent' is final so it cannot be overriden
//  }

// - b. Class is open, function is final
//  open class Parent { // class mark as open, so it can be inherited
//     fun name() = "base"
//  }
//
//  class Child : Parent() { // this is OK since class Parent is final
//    // this won't work: name' in 'Parent' is final and cannot be overridden
//    override fun name() = "Child"
//  }
// - c. Success: Class and function are open (marking both class and function as open)
open class Parent {
    open fun name() = "base"
}

class Child : Parent() {
    // this function needs the override modifier on `Child::name to override parent function
    override fun name() = "Child"
}

//      10.2: Closing an open method
// - consider this code
open class A {
    open fun foo() = "foo in A"
}

// removing the open class from  class B will make the class B final again making it no other class can inherit from it
// with an error: this type is final, so it cannot be inherited from
open class B : A() {
    // you can mark the function foo as final to disallow any class inheriting from B to override the function foo
    final override fun foo() = "foo in B"
    // override fun foo() = "foo in B"
}

open class C : B() {
    // this won’t compile because `foo` is marked
    // `final` in B
    // override fun foo() = "foo in C" // error: 'foo' in 'B' is final and cannot be overridden
}

fun main() {
    // 2. Kotlin instances of a class are created WITHOUT new key word
    // Its like calling a function call
    // Accessing constructor params with dot notation
    val f = Person("Jay", "Doe")
    val fullname = "${f.firstName} ${f.lastName}"
    // since lastName is of type `val` - you can access it via a getter but can't change it
    f.firstName = "Jane"
    // f.lastName = "Dee" -> val cannot be re-assigned
    // println("${f.firstName} ${f.lastName}")


    // code block 6.2
    val p4 = Person4("Person4")
    val s = Socket(2000, 3000)

    val p5 = Person5("Jane", "Doe")
    // populating kotlin class properties with values
    p5.toppings.add("Good")
   p5.toppings
    p5.fullName()

    // using a nested class
    val nestedClass = Person5.Nested().foo()
    println(nestedClass)
    val innerClass = Person5("Jane", "Doe").Inner().foo()

    // using custom getters and setters
    val person6 = Person6()
    person6.name = "Jane" // custom setter will be invoked
    person6.name //custom getter will be invoked

    // using default constructor parameter values
    println(Socket1(3000, 4000))
    val ss = Socket1(timeout = 2000, linger = 3000)
    println("timeout: ${ss.timeout} linger: ${ss.linger}")

}