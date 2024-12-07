document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.getElementById('search');
    const contractsTable = document.getElementById('contracts-table').getElementsByTagName('tbody')[0];
    const downloadBtn = document.getElementById('download-btn');
    let contracts = [];

    // Sacamos la informacion de nuestro propio backend utilizando fetch a '/api/contratos'
    function fetchContracts() {
        fetch('/api/contratos')
            .then(response => response.json())
            .then(data => {
                contracts = data;
                renderContracts(contracts);
            });
    }

    fetchContracts();


    function renderContracts(data) {
        contractsTable.innerHTML = '';
        data.forEach(contract => {
            const row = contractsTable.insertRow();
            Object.values(contract).forEach(text => {
                const cell = row.insertCell();
                cell.textContent = text;
            });
        });
    }

    searchInput.addEventListener('input', function () {
        const query = searchInput.value.toLowerCase();
        const filteredContracts = contracts.filter(contract =>
            Object.values(contract).some(value => value.toLowerCase().includes(query))
        );
        renderContracts(filteredContracts);
    });

    downloadBtn.addEventListener('click', function () {
        // Logic to download XML from backend
    });

});