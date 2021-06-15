package orbit.hello

import kotlinx.coroutines.runBlocking
import orbit.client.OrbitClient
import orbit.client.OrbitClientConfig
import orbit.client.actor.AbstractActor
import orbit.client.actor.ActorWithStringKey

interface Greeter : ActorWithStringKey {
    suspend fun sayHello(greeting: String): String
}


class GreeterImpl : AbstractActor(), Greeter {
    override suspend fun sayHello(greeting: String): String {
        println("Here: $greeting")

        return "You said: '${greeting}', I say: 'Hello from ${context.reference.key} at node ${context.client.nodeId?.key}!'"
    }
}

fun main() {
    runBlocking {
        val orbitClient = OrbitClient(
            OrbitClientConfig(
                namespace = "hello",
                grpcEndpoint = "dns:///localhost:50056/"
            )
        )

        orbitClient.start().join()

        val greeter = orbitClient.actorFactory.createProxy(Greeter::class.java, "Tim");
        val response = greeter.sayHello("Welcome to Orbit");

        println(response)

        orbitClient.stop().join()
    }
}