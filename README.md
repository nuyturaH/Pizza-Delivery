# Pizza-Delivery

The project is 2 page simple application, where user can can choose different flavours of pizzas, either half slice or one full pizza.
As an architecture in this project I've used Clean architectures + MVVM, and I took into account SOLID principles.  
These architectures have a lot of advantages when adding tests to a project. They were my choice of architecture because layers are decoupled and
each layer allows us to have its logic, without being tightly coupled with other layers. We have presentation layer that is basically Fragments, 
were we don't have any business logic. In the domain layer there are cases that can delegate their jobs to repositories, and repositories in their 
turn have access to local and remote data. I used a Repository pattern to provide scalability for the future data storing options.

For the asynchronous programming we have Kotlin Coroutines. For now it is the best clean and powerful library for working with Kotlin.

Tech stack is - Kotlin, Hilt, Moshi, Retrofit, Clean Architecture + MVVM, Kotlin Coroutines, Jetpack Navigation component. 

<p float="left">
  <img src="https://drive.google.com/uc?export=view&id=1B4PRrV46DDEYK4jikXn5LE_X10kfVjj0" style="width: auto; max-width: 100%; height: 360px"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="https://drive.google.com/uc?export=view&id=1xFXVcr7peJdDmixDtVUry-_7f5yffnzW" style="width: auto; max-width: 100%; height: 360px"/>
</p>
