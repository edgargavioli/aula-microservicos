document.addEventListener('DOMContentLoaded', async () => {

const users = await fetch('http://localhost:8081/usuarios')
    .then(response => response.json())
    .then(data => data.map(user => ({ id: user.id, nome: user.nome, email: user.email })));

const rooms = await fetch('http://localhost:8082/salas')
    .then(response => response.json())
    .then(data => data.map(room => ({ id: room.id, nome: room.nome, capacidade: room.capacidade })));

const reservations = await fetch('http://localhost:8083/reservas')
    .then(response => response.json())
    .then((data) => {
        return data.map(reservation => {
            const date = new Date(reservation.dataHora);
            const formattedDate = `${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear()} ${date.getHours()}:${date.getMinutes()}`;
            const user = users.find(user => user.id === reservation.usuario_id);
            const room = rooms.find(room => room.id === reservation.sala_id);

            return { id: reservation.id, usuario_id: user.nome, sala_id: room.nome, dataHora: formattedDate };
        })
    });

function populateTable(data, tableId) {
    const tableBody = document.getElementById(tableId);
    data.forEach(item => {
        const row = document.createElement('tr');
        Object.values(item).forEach(value => {
            const cell = document.createElement('td');
            cell.textContent = value;
            row.appendChild(cell);
        });
        tableBody.appendChild(row);
    });
}

populateTable(users, 'usersTable');
populateTable(rooms, 'roomsTable');
populateTable(reservations, 'reservationsTable');

});