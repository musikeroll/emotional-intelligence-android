# emotional-intelligence-android

App is written in Kotlin using MVVM, Dagger Hilt, Jetpack Compose and Jetpack Room.

Used Jetpack Compose to create modern UI, Dagger Hilt for dependency injection, 
and Jetpack Room for caching via local database.

Following MVVM's architecture, UI files reside in the feature/presentation, 
API integration (via local and and assets json file) reside in the feature/data,
and models and use cases reside in the feature/domain package.

Initial challenge was how to properly display levels and activities items 
based on objects received in json. But I managed to pull through using combination of 
LazyColumn and Row.

On the repository, I created LevelRepositoryAsset as concrete implementation 
of retrieving json from assets and saving it to local database. 
I also created a dummy implementation LevelRepositoryREST just to show
how to swap out LevelRepositoryAsset in future iterations.