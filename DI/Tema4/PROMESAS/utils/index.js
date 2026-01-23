const URL_PAISES =
  "https://www.thesportsdb.com/api/v1/json/123/all_countries.php";
const URL_EQUIPOS =
  "https://www.thesportsdb.com/api/v1/json/123/search_all_teams.php?s=Soccer&c=";
const URL_JUGADORES =
  "https://www.thesportsdb.com/api/v1/json/123/lookup_all_players.php?id=";

const selectPais = document.querySelector("#selectPais");
const contenedorEquipos = document.querySelector("#contenedorEquipos");
const listaJugadores = document.querySelector("#listaJugadores");
const tituloEquipo = document.querySelector("#tituloEquipo");
const nombrePaisSeleccionado = document.querySelector(
  "#nombrePaisSeleccionado"
);

document.addEventListener("DOMContentLoaded", () => {
  cargarPaises();

  selectPais.addEventListener("change", (evento) => {
    const pais = evento.target.value;
    if (pais) {
      cargarEquipos(pais);
    }
  });
});

async function cargarPaises() {
  try {
    const respuesta = await fetch(URL_PAISES);
    const datos = await respuesta.json();

    selectPais.innerHTML =
      '<option value="" selected disabled>Selecciona un país</option>';

    datos.countries.forEach((pais) => {
      const opcion = document.createElement("option");
      opcion.value = pais.name_en;
      opcion.textContent = pais.name_en;
      selectPais.appendChild(opcion);
    });
  } catch (error) {
    alert("Hubo un error cargando los países.");
  }
}

async function cargarEquipos(pais) {
  contenedorEquipos.innerHTML =
    '<div class="spinner-border text-primary" role="status"></div> Cargando equipos...';
  nombrePaisSeleccionado.textContent = `(${pais})`;

  limpiarJugadores();

  try {
    const respuesta = await fetch(`${URL_EQUIPOS}${encodeURIComponent(pais)}`);
    const datos = await respuesta.json();

    contenedorEquipos.innerHTML = "";

    if (!datos.teams) {
      contenedorEquipos.innerHTML =
        '<div class="alert alert-warning">No se encontraron equipos para este país.</div>';
      return;
    }

    datos.teams.forEach((equipo) => {
      const divCol = document.createElement("div");
      divCol.className = "col";

      const imagen =
        equipo.strTeamBadge ||
        "https://via.placeholder.com/150?text=Sin+Imagen";

      divCol.innerHTML = `
                <div class="card h-100 shadow-sm">
                    <img src="${imagen}" 
                         class="card-img-top" 
                         alt="${equipo.strTeam}"
                         onerror="this.onerror=null;this.src='https://via.placeholder.com/150?text=No+Disponible';">
                    <div class="card-body d-flex flex-column text-center">
                        <h5 class="card-title">${equipo.strTeam}</h5>
                        <p class="card-text small text-muted">${
                          equipo.strLeague || "Liga desconocida"
                        }</p>
                        <button 
                            class="btn btn-primary mt-auto btn-ver-plantilla" 
                            data-id="${equipo.idTeam}"
                            data-nombre="${equipo.strTeam}">
                            Ver Plantilla
                        </button>
                    </div>
                </div>
            `;
      contenedorEquipos.appendChild(divCol);
    });

    document.querySelectorAll(".btn-ver-plantilla").forEach((boton) => {
      boton.addEventListener("click", (e) => {
        const idEquipo = e.target.getAttribute("data-id");
        const nombreEquipo = e.target.getAttribute("data-nombre");
        cargarJugadores(idEquipo, nombreEquipo);
      });
    });
  } catch (error) {
    contenedorEquipos.innerHTML =
      '<div class="alert alert-danger">Error de conexión.</div>';
  }
}

async function cargarJugadores(idEquipo, nombreEquipo) {
  tituloEquipo.textContent = `Jugadores de: ${nombreEquipo}`;
  listaJugadores.innerHTML = '<li class="list-group-item">Cargando...</li>';

  try {
    const respuesta = await fetch(`${URL_JUGADORES}${idEquipo}`);
    const datos = await respuesta.json();

    listaJugadores.innerHTML = "";

    if (!datos.player) {
      listaJugadores.innerHTML =
        '<li class="list-group-item text-warning">No hay jugadores disponibles.</li>';
      return;
    }

    datos.player.forEach((jugador) => {
      const li = document.createElement("li");
      li.className = "list-group-item d-flex align-items-center";

      const avatar =
        jugador.strCutout ||
        jugador.strThumb ||
        "https://via.placeholder.com/50?text=User";

      li.innerHTML = `
                <img src="${avatar}" 
                     class="player-img me-3" 
                     alt="${jugador.strPlayer}"
                     onerror="this.onerror=null;this.src='https://via.placeholder.com/50?text=User';">
                <div>
                    <strong>${jugador.strPlayer}</strong>
                    <br>
                    <small class="text-muted">${jugador.strPosition}</small>
                </div>
            `;
      listaJugadores.appendChild(li);
    });
  } catch (error) {
    listaJugadores.innerHTML =
      '<li class="list-group-item text-danger">Error al cargar.</li>';
  }
}

function limpiarJugadores() {
  tituloEquipo.textContent = "Selecciona un equipo";
  listaJugadores.innerHTML = "";
}
