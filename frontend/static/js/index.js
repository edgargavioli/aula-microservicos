const usuarioForm = document.querySelector("#form-criar-usuario");
const salaForm = document.querySelector("#form-criar-sala");
const reservaForm = document.querySelector("#form-criar-reserva");

usuarioForm.addEventListener("submit", async (event) => {
  event.preventDefault();

  const formData = new FormData(usuarioForm);
  const data = Object.fromEntries(formData.entries());

  try {
    const response = await fetch("http://localhost:8081/usuarios", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });

    alert("Usuário criado com sucesso!");
    usuarioForm.reset();
  } catch (error) {
    alert("Erro:", error);
  }
});

salaForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    
    const formData = new FormData(salaForm);
    const data = {
        nome: formData.get("nomeSala"),
        capacidade: formData.get("capacidade"),
    };

    console.log(data);
    
    try {
        const response = await fetch("http://localhost:8082/salas", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
        });
    
        
        alert("Sala criada com sucesso!");
        salaForm.reset();

    } catch (error) {
        alert("Erro:", error);
    }
});

reservaForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    
    const formData = new FormData(reservaForm);

    const date = formData.get("dataHora");
    const [year, month, day] = date.split("-");
    const formatedDate = `${year}-${month}-${day}:00`;

    const data = {
      dataHora: formatedDate,
      usuario_id: Number(formData.get("usuarioSelect")),
      sala_id: Number(formData.get("salaSelect")),
    };
    
    try {
        const response = await fetch("http://localhost:8083/reservas", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
        });
        
        alert("Reserva criada com sucesso!");
        reservaForm.reset();
    } catch (error) {
        alert("Erro:", error);
    }
});

function populaSelectUsuarios() {
    fetch("http://localhost:8081/usuarios")
        .then((response) => response.json())
        .then((data) => {
            const select = document.getElementById("usuarioSelect");
            data.forEach((usuario) => {
                const option = document.createElement("option");
                option.value = usuario.id;
                option.textContent = usuario.nome;
                select.appendChild(option);
            });
        });
}

function populaSelectSalas() {
    fetch("http://localhost:8082/salas")
        .then((response) => response.json())
        .then((data) => {
            const select = document.getElementById("salaSelect");
            data.forEach((sala) => {
                const option = document.createElement("option");
                option.value = sala.id;
                option.textContent = sala.nome;
                select.appendChild(option);
            });
        });
}

document.addEventListener("DOMContentLoaded", () => {
    populaSelectUsuarios();
    populaSelectSalas();
});