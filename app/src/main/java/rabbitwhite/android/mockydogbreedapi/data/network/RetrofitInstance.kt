// Define the package where this class belongs.
// This helps organize networking-related code within the project structure.
package rabbitwhite.android.mockydogbreedapi.data.network

// Import the DogApiService interface to create an instance of it using Retrofit.
import DogApiService

// Import Retrofit and GsonConverterFactory.
// Retrofit is a popular HTTP client for making network requests in Android.
// GsonConverterFactory converts JSON responses into Kotlin objects using the Gson library.
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Define the `RetrofitInstance` object as a singleton.
// This ensures only one instance of Retrofit is created and used throughout the app.
object RetrofitInstance {

    // Create a `val` property `api` of type `DogApiService`.
    // The `by lazy` keyword ensures the instance is created only when first accessed (lazy initialization).
    val api: DogApiService by lazy {

        // Build a Retrofit instance using the Builder pattern.
        Retrofit.Builder()
            // Set the base URL for the API calls. This is the root URL for Mocky.io endpoints.
            .baseUrl("https://run.mocky.io/v3/")

            // Add a converter factory to convert JSON responses into Kotlin data classes using Gson.
            .addConverterFactory(GsonConverterFactory.create())

            // Call `build()` to create the Retrofit instance with the specified configuration.
            .build()

            // Create an implementation of the `DogApiService` interface.
            .create(DogApiService::class.java)
    }
}
