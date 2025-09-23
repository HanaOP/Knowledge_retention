const employeeList = document.getElementById('employeeList');
const employeeForm = document.getElementById('employeeForm');
const message = document.getElementById('message');

function fetchEmployees() {
    fetch('http://localhost:8080/employees')
        .then(response => response.json())
        .then(data => {
            employeeList.innerHTML = '';
            data.forEach(emp => {
                const li = document.createElement('li');
                li.textContent = `${emp.name} — ${emp.department} (${emp.status})`;
                employeeList.appendChild(li);
            });
        })
        .catch(err => {
            message.textContent = 'Error fetching employees.';
            console.error(err);
        });
}
fetchEmployees();

employeeForm.addEventListener('submit', (event) => {
    event.preventDefault();

    const empData = {
        name: document.getElementById('name').value,
        department: document.getElementById('department').value,
        status: document.getElementById('status').value
    };

    fetch('http://localhost:8080/employees', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(empData)
    })
    .then(response => {
        if (response.ok) {
            message.textContent = 'Employee added successfully.';
            employeeForm.reset();
            fetchEmployees();
        } else {
            message.textContent = 'Failed to add employee.';
        }
    })
    .catch(err => {
        message.textContent = 'Error adding employee.';
        console.error(err);
    });
});
