import java.lang.NumberFormatException
import java.util.InputMismatchException

fun main(args: Array<String>) {
    val PerfilesUsuarios = mutableListOf(
        PerfilUsuario(1, "Sergio", "Orellana", "https://instagram/Sergio", 18, "sergio2@gmail.com", null, "Pendiente"),
        PerfilUsuario(2, "María", "Gómez", null, 30, "maria@gmail.com", "Amante de los libros", "Pendiente"),
        PerfilUsuario(3, "Carlos", "López", null, 22, "carlos@gmail.com", "Estudiante de música", "Activo"),
        PerfilUsuario(4, "Francisco", "Anzueto", null, 25, "francisco@gmail.com", null, "Inactivo"),
        //Este perfil sirve para que observe que cuando el estado es diferente a los 3 permitidos tira un mensaje a consola
        //PerfilUsuario(5, "Adrian", "Pascual", null, 19, "adrian@gmail.com", null, "Jugando")
    )
    var id = PerfilesUsuarios.size
    var continuar = true
    while (continuar){
        println("Seleccione alguna de las siguientes opciones: \n1. Crear Perfil \n2. Buscar perfil de usuario(s) \n3. Eliminar perfil \n4. Agregar Hobby \n5. Salir")
        print("Opcion seleccionada: ")
        val opcion = readln().toIntOrNull()
        when (opcion){
            //Crear perfil
            1 -> {
                try {
                    println("Ingrese la información del perfil:")
                    print("Nombres: ")
                    val nombres = readln()
                    print("Apellidos: ")
                    val apellidos = readln()
                    print("Url de foto de perfil (deje vacío para nulo): ")
                    val urlPhoto = readln().ifBlank { null }
                    print("Edad: ")
                    val edad = readln().toInt()
                    print("Correo: ")
                    val correo = readln()
                    print("Biografía (deje vacío para nulo): ")
                    val biografía = readln().ifBlank { null }

                    var estado: String
                    // Validar estado
                    // Esto sirve para que se repita hasta que el usuario ingrese correctamente alguna de las tres opciones valida para de esta manera evitar que el usuario tenga que repetir el ingreso de datos nuevamente
                    do {
                        print("Estado (Activo, Pendiente o Inactivo): ")
                        estado = readlnOrNull()?.trim()?.lowercase().toString()
                    } while (estado !in listOf("activo", "pendiente", "inactivo"))
                    println(id)
                    id++
                    val nuevoPerfil = PerfilUsuario(id, nombres, apellidos, urlPhoto, edad, correo, biografía, estado)
                    PerfilesUsuarios.add(nuevoPerfil)
                    println("Perfil creado exitosamente.")
                } catch (e: NumberFormatException){
                    println("Error al crear el perfil, has ingresado un caracter y se esperaba un numero entero: ${e.message}")
                } catch (e: Exception) {
                    println("Error al crear el perfil: ${e.message}")
                }


            }
            //Buscar perfil
            2 -> {
                try {
                    print("Ingrese el ID o nombres y/o apellidos a buscar: ")
                    val search = readlnOrNull()?.trim() ?: throw IllegalArgumentException("Búsqueda inválida")

                    // Filtrar perfiles según el ID o nombres y/o apellidos
                    val resultados = PerfilesUsuarios.filter {
                        it.ID.toString() == search || it.Nombres.contains(search, ignoreCase = true) || it.Apellidos.contains(search, ignoreCase = true)
                    }

                    if (resultados.isEmpty()) {
                        println("No se encontraron perfiles con la información ingresada.")
                    } else {
                        for (perfil in resultados) {
                            println("ID: ${perfil.ID}")
                            println("Nombres: ${perfil.Nombres}")
                            println("Apellidos: ${perfil.Apellidos}")
                            println("Url de foto de perfil: ${perfil.UrlPhoto ?: "Nulo"}")
                            println("Edad: ${perfil.Edad}")
                            println("Correo: ${perfil.Correo}")
                            println("Biografía: ${perfil.Biografia ?: "Nulo"}")
                            println("Estado: ${perfil.estado}")
                            if (perfil.Hobbies.isNotEmpty()) {
                                println("Hobbies:")
                                for (hobby in perfil.Hobbies) {
                                    println("  Título: ${hobby.Titulo}")
                                    println("  Descripción: ${hobby.Descripcion}")
                                    println("  Url de foto: ${hobby.UrlPhoto ?: "Nulo"}")
                                }
                            }
                            println()
                        }
                    }
                } catch (e: Exception) {
                    println("Error al buscar el perfil: ${e.message}")
                }
            }
            3 -> {
                try {
                    print("Ingrese el ID del perfil a eliminar: ")
                    val id_Eliminar = readln().toIntOrNull()
                    val EliminarPerfil = PerfilesUsuarios.find { it.ID == id_Eliminar }
                    if (EliminarPerfil != null) {
                        PerfilesUsuarios.remove(EliminarPerfil)
                        println("Perfil con ID $id_Eliminar eliminado exitosamente.")
                    } else {
                        println("No se encontró un perfil con el ID $id_Eliminar.")
                    }
                } catch (e: Exception) {
                    println("Error al eliminar el perfil: ${e.message}")
                }
            }
            4 -> {
                try {
                    print("Ingrese el ID o nombres y/o apellidos del perfil para agregar un Hobby: ")
                    val query = readlnOrNull()?.trim().toString()
                    val perfilEncontrado = PerfilesUsuarios.find {
                        it.ID.toString() == query || it.Nombres.contains(query, ignoreCase = true) || it.Apellidos.contains(query, ignoreCase = true)
                    }
                    if (perfilEncontrado != null) {
                        println("Perfil encontrado: ${perfilEncontrado.Nombres} ${perfilEncontrado.Apellidos}. Ingrese la información del hobby:")
                        print("Título: ")
                        val título: String = readln()
                        print("Descripción: ")
                        val descripción: String = readln()
                        print("Url de foto del hobby (deje vacío para nulo): ")
                        val urlPhoto = readln().ifBlank { null }
                        val nuevoHobby = Hobby(título, descripción, urlPhoto)
                        perfilEncontrado.AgregarHobby(nuevoHobby)
                        println("Hobby agregado exitosamente al perfil.")
                    } else {
                        println("No se encontró un perfil con la información ingresada.")
                    }
                } catch (e: Exception) {
                    println("Error al agregar el Hobby: ${e.message}")
                }
            }
            5 ->{
                continuar = false
                println("Gracias por utilizar mi programa, hasta luego")
            }
            else -> println("Opción inválida, por favor ingrese un número válido.")
        }
    }
}