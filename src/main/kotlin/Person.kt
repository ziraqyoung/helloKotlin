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

// 3. Constructor parameters visibility
// class constructor params are defined in the class header
// `var` means values are mutable -have both getters and setters (read-from and written-to) while val values are immutable (read-only)


// 4. Annotations and visibility modifiers
// If the class constructor has annotations or visibility modifiers, the constructor keyword is required
// example: modified goes before constructor keyword
// WTF TODO: is @Inject ????
// Kotlin visibility modifiers are `private`, `protected`, `internal`, `public`
// classes, objects, interfaces, constructors, functions and properties and their setters have visibility modifiers
// `internal` modifiers is visible anywhere inside the same module

// class Customer public @Inject constructor(name: String)

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
}