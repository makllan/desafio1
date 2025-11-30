import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import logo from './assets/logo.png';

function Admin() {
    const [list, setList] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get('http://localhost:8080/work/list')
            .then(response => {
                setList(response.data);
            })
            .catch(error => {
                console.error("Erro ao buscar lista", error);
                alert("Erro ao buscar lista de registros");
            });
    }, []);

    return (
        <div className="container">
            <div className="card wide">
                <div className="header-row">
                    <div style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
                        <img src={logo} alt="Logo" className="logo-small" />
                        <h1>Registros Administrativos</h1>
                    </div>
                    <button onClick={() => navigate('/dashboard')}>Voltar</button>
                </div>
                <table>
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
                        {list.map((item) => (
                            <tr key={item.id}>
                                <td>{item.id}</td>
                                <td>{item.employeeId}</td>
                                <td>{item.checkinTime}</td>
                                <td>{item.checkoutTime}</td>
                                <td>{item.formattedDuration}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default Admin;
