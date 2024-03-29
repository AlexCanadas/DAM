let inputNombre = document.querySelector("input#input-nombre");
let inputApellido = document.querySelector("input#input-apellido");
let inputEmail =document.querySelector("input#input-email");
let inputDepartamento = document.querySelector("#departamento");
let boton = document.querySelector("#boton-confirmar");
let listaUsuariosDatos = [];
let listaTrabajadores = document.querySelector("#lista-trabajadores");
let numIT = document.querySelector("#numIT");
let numMarketing = document.querySelector("#numMarketing");
let numVentas = document.querySelector("#numVentas");
let numAdministracion = document.querySelector("#numAdministracion"); 

// Selecciona los elementos de la tabla por sus IDs
let departamentoIT = document.getElementById("departamentoIT");
let departamentoMarketing = document.getElementById("departamentoMarketing");
let departamentoVentas = document.getElementById("departamentoVentas");
let departamentoAdministracion = document.getElementById("departamentoAdministracion");

boton.addEventListener("click", () => {
    // Verificamos que se hayan ingresado todos los datos necesarios
    if (
        inputNombre.value.length > 0 &&
        inputApellido.value.length > 0 &&
        inputEmail.value.length > 0 &&
        inputDepartamento.value !== "Escoje al departamento al que perteneces"
    ) {
        // Creamos el usuario con los datos ingresados
        let usuario = {
            nombre: inputNombre.value,
            apellido: inputApellido.value,
            email: inputEmail.value,
            departamento: inputDepartamento.value
        };
    
        // Agregar el usuario al array listaUsuariosDatos
        listaUsuariosDatos.push(usuario);

        // Actualizar la lista de trabajadores generales
        actualizarListaTrabajadores(usuario);

        // Actualizar la lista de trabajadores por departamento
        actualizarTrabajadoresPorDepartamento(usuario);

       // Actualizar numero de trabajadores por departamento
        actualizarNumeroTrabajadoresPorDepartamento()

        // Limpiar el formulario
        inputNombre.value = "";
        inputApellido.value = "";
        inputEmail.value = "";
        inputDepartamento.value = "Escoje al departamento al que perteneces";

        // Mostrar un mensaje de éxito si fue todo bien
        Swal.fire({
            title: '¡Correcto!',
            text: 'Usuario agregado correctamente',
            icon: 'success',
            confirmButtonText: 'Gracias',
            timer: 2000
        });
    } else {
        // Mostrar un mensaje de error si falta algún dato
        Swal.fire({
            title: '¡Error!',
            text: 'Falta algún dato',
            icon: 'error',
            confirmButtonText: 'Probar de nuevo',
            timer: 2000
        });
    }
});

// Función para actualizar la lista de trabajadores generales
function actualizarListaTrabajadores(usuario) {
    let nodo = document.createElement("li");
        nodo.textContent = `${usuario.nombre} ${usuario.apellido}`;
        nodo.classList.add("animate__animated", "animate__rollIn", "list-group-item");
        listaTrabajadores.append(nodo); 
} 

function actualizarTrabajadoresPorDepartamento(usuario) {
    let nodo = document.createElement("div");
    nodo.textContent = `${usuario.nombre} ${usuario.apellido} - ${usuario.email}`;
    nodo.classList.add("animate__animated", "animate__rollIn", "list-group-item");

    switch (usuario.departamento) {
        case 'IT':
            departamentoIT.append(nodo);
            break;
        case 'Marketing':
            departamentoMarketing.append(nodo);
            break;
        case 'Ventas':
            departamentoVentas.append(nodo);
            break;
        case 'Administración':
            departamentoAdministracion.append(nodo);
            break;
    }
}

// Función para actualizar el número de trabajadores por departamento en las columnas correspondientes
function actualizarNumeroTrabajadoresPorDepartamento() {
    let _numIT = listaUsuariosDatos.filter(usuario => usuario.departamento === 'IT').length;
    let _numMarketing = listaUsuariosDatos.filter(usuario => usuario.departamento === 'Marketing').length;
    let _numVentas = listaUsuariosDatos.filter(usuario => usuario.departamento === 'Ventas').length;
    let _numAdministracion = listaUsuariosDatos.filter(usuario => usuario.departamento === 'Administración').length;

// Mostramos números actualizados
    numIT.textContent =`Número de trabajadores: ${_numIT}`;
    numMarketing.textContent = `Número de trabajadores: ${_numMarketing}`;
    numVentas.textContent = `Número de trabajadores: ${_numVentas}`;
    numAdministracion.textContent = `Número de trabajadores: ${_numAdministracion}`;
}
