import java.lang.IllegalArgumentException

class PerfilUsuario(
    val ID: Int,
    val Nombres: String,
    val Apellidos: String,
    val UrlPhoto: String?,
    val Edad: Int,
    val Correo: String,
    val Biografia: String?,
    estado: String
){

    // Validaciones que tiene que cumplir la variable estado
    var estado: String = ""
        set(value) {
            if(value.lowercase() in listOf("activo", "pendiente", "inactivo")){
                field = value.lowercase()
            }else {
                //Sirve para mostrar una excepción al usuario de que no ingresó el valor esperado, siendo "Activo", "Pendiente", "Inactivo"
                throw IllegalArgumentException("Entrada inválida, el estado debe ser 'Activo', 'Pendiente' o 'Inactivo'")
            }
        }
    init {
        // Establecer el valor del estado utilizando el setter personalizado
        this.estado = estado
    }


    //La lista de hobbies
    val Hobbies = mutableListOf<Hobby>()

    //Sirve para agregar un hobby
    fun AgregarHobby(hobby: Hobby){
        Hobbies.add(hobby)
    }



}