document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.getElementById('search');
    const contractsTable = document.getElementById('contracts-table').getElementsByTagName('tbody')[0];
    const downloadBtn = document.getElementById('download-btn');

    // Sample data for demonstration
    const contracts = [
        {
            NIF: '12345678A',
            Adjudicatario: 'Company A',
            ObjetoGenerico: 'Service',
            Objeto: 'Consulting',
            Fecha: '2023-01-01',
            Importe: '1000',
            Proveedores: '3'
        },
        {
            NIF: '87654321B',
            Adjudicatario: 'Company B',
            ObjetoGenerico: 'Product',
            Objeto: 'Software',
            Fecha: '2023-02-01',
            Importe: '2000',
            Proveedores: '5'
        }
    ];

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

    // Initial render
    renderContracts(contracts);
});