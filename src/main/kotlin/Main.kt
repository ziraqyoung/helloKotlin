// You can also define global variables
// top-level or global variables
// var greeting: String? = null

fun main() {
    // 1. Defining variables in Kotlin
    // val are assigned once(read-only),
    // use var if you want to re-assign a variable use var instead of val  - var name: String = "Nate
    // NB: this is a local variable - available within the scope of this function
    val name: String = "Nate"
    var greeting: String? = null

    // 2. Variable types in Kotlin
    // consider var name: String = "Nate" - name variable is of type String
    // types in Kotlin are non-null by default, you can have this - var name: String = null
    // there is a difference b2in a String and a null String
    // Add ? after variable type to allow null value
    val language: String? = null // nullable variable can have null or actual value

    // 3. Kotlin support for Type Inference - Can infer types of value and properties
    // Although for nullable type, Types MUST be declared explicitly - code below won't work
    // val str = "US"
    // str = null
    // Do not need to explicitly set a variable as a String
    val num = 10 // No explicit setting of the type as Int
    var code: String? = "US" // No explicit setting type as String, for null, Type must be declared

    // 4. Control Flow in Kotlin

    // 4.1 IF statements - simple control flow in Kotlin
    if (language != null) {
        // println(language)
    } else {
        // println("No language specified..")
    }
    // 4.2 WHEN statement - similar to SWITCH statement in Java
    // syntax: pass variable to when(), then add value -> ....
    // Do something when code is "US"
    when(code) {
        // when code == null -> Do print the statement
        // null -> println("Value for code is null")
        // for default (else) -> print code
        // else -> println(code)
    }

    // 4.3 Using IF and WHEN as expression to assign a value depending on passed logical conditions
    // Using IF -> if(expression) do_something else do_something_else
    // Using WHEN -> when(expression) -> value -> do_something else -> do_something_else
    greeting = "Hello"
    val greetingToPrint = if(greeting != null) greeting else "Hi"
    val secondGreetingToPrint = when(greeting) {
        null -> "Hi"
        else -> greeting
    }
    // println(secondGreetingToPrint)

    // calling a function
    // println(getGreeting()) // pass the returned value to println function
    // sayHello() // this code actually does the printing
    // println(returnNullString())
    // println(sayHelloSimplified())
    // greetWithParams("Kotlin")
    // greetWithParams1("Bonjour", "From SEF")

    // 6. Collections and Iterations in Kotlin
    // these are things like: Arrays, Lists and Maps
    // arrayOf can infer the array you want to create eg Strings
    val interestingThings = arrayOf("Kotlin", "Programming", "Comic Books")
    interestingThings.size
    interestingThings.get(0) // get value occupied by a particular index
    interestingThings[2] // get a value for a  given index

    // We can loop over a given array with a basic for loop - using for....in
    // this for loop is similar to what's available in Java
    for(interestingThing in interestingThings) {
        interestingThing // do something with each value of interestingThing variable
    }
    // Kotlin support for HOF(higher order functions) means we can write function in a declarative way
    // Functions in Kotlin are first class citizen
    // forEach() - yields each element in the iteration as it: String
    // calling interestingThing -> is what is called lambda syntax in Kotlin : If you have a function that its only
    // parameter is another function then you can omit the parenthesis
    interestingThings.forEach { interestingThing -> // to rename the name passed instead of it: String use: `nameOfTheThing ->`
        // lambda expression does not yield the index data for current item, for index use forEachIndexed instead of forEach
        interestingThing // `it` is the default name of the item passed to this lambda function
    }
    interestingThings.forEachIndexed { index, interestingThing -> // get the current item and the index
        "$interestingThing is at index $index"
    }
    // listOf() instead of arrayOf() function has many methods since we are working with list instead of an array
    // Basically listOf() has more methods that arrayOf() - both are used to create indexed collections
    val listOfCars = listOf("Mazda", "Fielder", "Subaru", "Noah")
    listOfCars[0]
    listOfCars.get(0)

    separatorLine()
    listOfCars.forEach { interestingThing -> println(interestingThing)}

    // mapOf() create pairs - key value map similar to hash in Ruby
    val map = mapOf(1 to 'a', 2 to 'b', 3 to 'c') // map with 3 pairs of value in it
    val mutableMapOf = mutableMapOf(1 to 'a', 2 to 'b', 3 to 'c') // map with 3 pairs of value in it
    // keys are 1,2,3 and values are 'a', 'b', 'c'
    // to iterate over the map
    // listOfCars.forEach { interestingThing -> println(interestingThing)}
    separatorLine()
    map.forEach { key, value -> println("$key -> $value") }
    // use `put` in mutableMapOf() to add a new key/value pair to a map
    mutableMapOf.put(4, 'd') // or mutableMapOf[4] = "d"
    // by this far we have defined several types of collection Arrays, Lists and Maps

    // Kotlin differentiates between mutable and immutable collection types
    // By default a collection type in Kotlin is immutable - can't add or subtract values from the collection once initially created
    // lets try adding anything to the listOf, listOf has no `add` method - immutable by default
    // Use `mutableTypeOfSomething` eg mutableListOf to allow mutating of collections in Kotlin
    // Others are `mutableArrayOf(), mutableMapOf()
    separatorLine()
    val mutableListOfCars = mutableListOf("AE 102", "Sylph", "X-trail")
    println(mutableListOfCars.size)
    mutableListOfCars.add("FJ 90")
    println(mutableListOfCars.size)

    separatorLine()
    val vehicles = listOf("AE 102", "Sylph", "X-trail")
    sayHelloToMultipleThings("Hi", vehicles)
    // since the second arg of this function is vararg, you do not need the second argument
    // sayHelloToMultipleThingsWithVararg("Hi"), but with arguments
    // -> sayHelloToMultipleThingsWithVararg("Hi", "Kotlin", "Ruby")
    separatorLine()
    sayHelloToMultipleThingsWithVararg("Hi", "Ruby", "Kotlin")
    // Note that trying to pass arrayOf("things") to sayHelloToMultipleThingsWithVararg() as 2nd argument, wont work with error `Required string found Array`
    // To pass array of existing values to the vararg argument - you do it with a spread operator
    // spread arrayOf() values, with *representationOfYourArrayOf () - spread operator doesn't work with listOf()
    separatorLine()
    val bestVehicles = arrayOf("BMW", "Subaru", "Prado")
    sayHelloToMultipleThingsWithVararg("Hello", *bestVehicles)

    // names parameter for code clarity `name = "Jay" to use named parameters
    // this allows to mix up the order in which arguments are passed
    separatorLine()
    greetPerson(greeting = "Hi", name = "Jay")
    // Using names parameters with default values to reuse code
    greetPerson(name = "Matzo")
    greetPerson(name = "Lisa", greeting = "Good Morning")
}

// 5. Basic Functions in Kotlin
// Both main() and println are functions - println accepts an argument to print
// Just like Kotlin variables, function can specify return type & use return keyword for them to return
// main function has no return type specified, In Kt the return type of such a function is Unit which is omitted, that is
// it does stuff without return anything -> see the sayHello()
// this function (getGreeting) is called with brackets -> getGreeting()
fun getGreeting(): String {
    return "Hello from getGreeting()"
}

// this function MUST NOT have a return keyword as we set to not return anything
// You can omit Unit as a return type hence change this code
// fun sayHello(): Unit {....} to fun sayHello() {....}
fun sayHello() { // same as our runnable main function
    println("Hello from sayHello()")
}

// We can also return null or more correctly a nullable string just like in variables
// just signature function return value as nullable string just like it's done for variables
fun returnNullString(): String? {
    return null
}

// SINGLE-EXPRESSION FUNCTIONS in Kotlin- You can leverage type inference in a single expression function by omitting the return type
// from fun sayHelloSimplified(): String = "Hello form sayHelloSimplified()" to
// fun sayHelloSimplified() = "Hello form sayHelloSimplified()"
// Functions in Kt when we are returning a single value can be simplified into  single expression
// replace curly braces {} with = to convert a function into a single expression
fun sayHelloSimplified() = "Hello form sayHelloSimplified() with Single expression and type inference"

// Function parameters in Kotlin
fun greetWithParams(itemToGreet: String) {
    // using literal interpolation -> String templates allows substituting values in a pre-defined template
    // This is called  TEMPLATED VALUE
    val msg = "Hello $itemToGreet" // Example of String template in Kotlin
    println(msg)
}

// since these functions are not enclosed in a class -> they are top-level functions
fun greetWithParams1(greeting: String, itemToGreet: String) = println("$greeting $itemToGreet") // Using single expression in a function with params
fun separatorLine() = println("=".repeat(50))

// this shows that you can pass a collection type to a function in Kotlin as a parameter
fun sayHelloToMultipleThings(greeting: String, itemsToGreet: List<String>) {
    itemsToGreet.forEach { item ->
        println("$greeting $item")
    }
}

// 7. vararg, named arguments & default parameter values
// varargs parameter for a function in Kotlin - a keyword that represent a variable number of arguments
// update the above sayHelloToMultipleThings to accepts the second args as `any number of argument instead of a List<String>
// With vararg, itemsToGreet will be shuffled into an array - supports 0, 1 or any other number of arguments
fun sayHelloToMultipleThingsWithVararg(greeting: String, vararg itemsToGreet:String) {
    itemsToGreet.forEach { item ->
        println("$greeting $item")
    }
}

// Kotlin also support named arguments - which helps to get out of clamming the order in which arguments are passed e.g. in Code Reviews
// To call this function: greetPerson("Hi", "Jay")
// To call the function greetPerson(greeting = "Hi", name = "Jay")
// with names arguments you can leverage default values
// This feature (named parameter & default values) allows leveraging the builder pattern - avoiding the need to write getters and setters
// and private constructors
// Configure and reuse functions and objects by leveraging default values and named arguments syntax
fun greetPerson(greeting: String = "Bonjour", name: String) = println("$greeting $name")


// NB named arguments and default have limitations in that `Mixing names paramters and positional arguments is not allowed`
// val gits = arrayOf("Kotlin", "Programming", "Comic books")
// this will work -> sayHelloToMultipleThingsWithVararg(greeting = "Hi", itemsToGreet = *gits)
// this will WONT work(mixing positions args with named args -> sayHelloToMultipleThingsWithVararg(greeting = "Hi", *gits)
