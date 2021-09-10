
const createButton = document.querySelector('#crear');
createButton.addEventListener('submit',async (e)=>{
    e.preventDefault();
    const Trip = {
        nombre: crearNombre.value,
        fecha: crearFecha.value,
        lugarSalida: crearSalida.value,
        lugarLlegada: crearLlegada.value,
    };
    try {
        
        const response = await fetch(`http://localhost:8080/myapp/Trips`, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(Trip)
        });
        console.log(response);
        await DisplayTrips();
    } catch (error) {
        console.log("error en crear"+error);
    }
});

const editButton = document.querySelector('#editar');
editButton.addEventListener('submit',async (e)=>{
    e.preventDefault();
    const Trip = {
        nombre: editNombre.value,
        fecha: editFecha.value,
        lugarSalida: editSalida.value,
        lugarLlegada: editLlegada.value,
    };
    const  id = editButton.getAttribute("valor");
    try {
        const response = await fetch(`http://localhost:8080/myapp/Trips/${id}`, {
            method: 'PUT',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(Trip)
        });
        await DisplayTrips();
    } catch (error) {
        console.log("Error en editar"+error)
    }
});



async function getData() {
    try {
    const response = await fetch("http://localhost:8080/myapp/Trips", {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    })
    const data = await response.json();
    console.log(data);
    return data;
    } catch (error) {
        console.log("No hay datos"+error)  
    } 
}


function fillHtml(id,nombre,fecha,lugarSalida,lugarLlegada)
{
    const html = `
    <tr>
    <td>${id}</td>
    <td>${nombre}</td>
    <td>${fecha}</td>
    <td>${lugarSalida}</td>
    <td>${lugarLlegada}</td>
    <td>
    <a valor=${id} href="#editTripModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
        <button
        class="delete"
        id=${id}
        data-toggle="modal"
        ><i
            class="material-icons"
            data-toggle="tooltip"
            title="Delete"
            >&#xE872;</i
        ></button>

    </td>
    </tr>`;
    return html;
}

function addDeleteListener()
{
    const deleteList = document.getElementsByClassName('delete');
    for (let index = 0; index < deleteList.length; index++) {
        const element = deleteList[index];
        element.addEventListener('click',async ()=>{
            try {
                
                const response = await fetch(`http://localhost:8080/myapp/Trips/${element.id}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-type': 'application/json'
                    }
                });
                await DisplayTrips();
            } catch (error) {
                console.log("error en eliminar"+error)
            }
        });
    }
}

function addEditListener()
{
    const deleteList = document.getElementsByClassName('edit');
    for (let index = 0; index < deleteList.length; index++) {
        const element = deleteList[index];
        element.addEventListener('click', ()=>{
            const formElement = document.querySelector("#editar");
            formElement.setAttribute("valor", element.getAttribute("valor"));
        });
    }
}

async function DisplayTrips() {
    const response = await getData();
    console.log(response);
    let newString = "";

    response.forEach((element) => {  
        newString += fillHtml(element.id,element.name,element.date,element.departure,element.arrival);
    
    });
    document.querySelector('tbody').innerHTML = newString;
    addDeleteListener();
    addEditListener();
}


DisplayTrips();