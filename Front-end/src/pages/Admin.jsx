import { useEffect, useState } from 'react';
import api from '../services/api';

function Admin() {
  const [records, setRecords] = useState([]);

  useEffect(() => {
    const fetchRecords = async () => {
      try {
        const response = await api.get('/work/list');
        setRecords(response.data);
      } catch (error) {
        console.error('Erro ao buscar registros');
      }
    };
    fetchRecords();
  }, []);

  return (
    <div className="admin-container">
      <h1>Registros de Ponto</h1>
      <table className="records-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Func. ID</th>
            <th>Check-in</th>
            <th>Check-out</th>
            <th>Duração</th>
          </tr>
        </thead>
        <tbody>
          {records.map((rec) => (
            <tr key={rec.id}>
              <td>{rec.id}</td>
              <td>{rec.employeeId}</td>
              <td>{new Date(rec.checkinTime).toLocaleString()}</td>
              <td>{rec.checkoutTime ? new Date(rec.checkoutTime).toLocaleString() : '-'}</td>
              <td>{rec.formattedDuration || '-'}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Admin;
