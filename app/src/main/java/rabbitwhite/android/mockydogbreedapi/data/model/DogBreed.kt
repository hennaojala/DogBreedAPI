// Define the package where this class belongs.
// This helps organize the code and avoid naming conflicts.
package rabbitwhite.android.mockydogbreedapi.data.model

// The `HikingPlace` class is a data class. Data classes in Kotlin are designed to hold data
// and automatically provide useful methods like `toString()`, `equals()`, and `hashCode()`.
// The `DogBreed` class is a data class, designed to hold data about dog breeds
// and automatically provide useful methods like `toString()`, `equals()`, and `hashCode()`.
data class DogBreed(

    // The `name` property represents the name of the dog breed.
    // It's of type `String`, meaning it holds textual data.
    val name: String,

    // The `origin` property specifies the country or region where the breed originated.
    // It is also a `String` to represent location details.
    val origin: String,

    // The `size` property indicates the size of the breed (e.g., "Small", "Medium", "Large").
    // It uses a `String` to allow different size classifications.
    val size: String,

    // The `coat` property indicates the type of coat the breed has (e.g., "Short", "Medium", "Curly").
    // This is a `String` to describe the breed's coat characteristics.
    val coat: String,

    // The `temperament` property is a list of special traits or behaviors associated with the breed.
    // It uses a `List<String>` to store multiple temperament descriptions.
    val temperament: List<String>,

    // The `lifeExpectancy` property represents the breed's average lifespan, typically in years.
    // This is a `String` that specifies the life expectancy range (e.g., "10-12 years").
    val lifeExpectancy: String,

    // The `description` property provides a detailed description of the breed.
    // This is a `String` for longer textual information.
    val description: String
)
