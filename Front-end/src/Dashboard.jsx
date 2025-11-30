import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import logo from './assets/logo.png';

function Dashboard() {
    const [user, setUser] = useState(null);
    const [lastWork, setLastWork] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const storedUser = localStorage.getItem('user');
        if (!storedUser) {
            navigate('/');
        } else {
            setUser(JSON.parse(storedUser));
        }
    }, [navigate]);

    const handleCheckIn = async () => {
        if (!user) return;
        try {
            const response = await axios.post(`http://localhost:8080/work/checkin/${user.id}`);
            alert('Check-in realizado com sucesso!');
            console.log(response.data);
        } catch (error) {
            if (error.response && error.response.data) {
                alert(error.response.data);
            } else {
                alert('Erro ao fazer check-in');
            }
            console.error(error);
        }
    };

    const handleCheckOut = async () => {
        if (!user) return;
        try {
            const response = await axios.post(`http://localhost:8080/work/checkout/${user.id}`);
            setLastWork(response.data);
            alert(`Check-out realizado! Tempo trabalhado: ${response.data.formattedDuration}`);
        } catch (error) {
            if (error.response && error.response.data) {
                alert(error.response.data);
            } else {
                alert('Erro ao fazer check-out');
            }
            console.error(error);
        }
    };

    if (!user) return <div>Carregando...</div>;

    return (
        <div className="container">
            <div className="card">
                <img src={logo} alt="Logo" className="logo" />
                <h1>Olá, {user.name}</h1>
                <div className="button-group">
                    <button onClick={handleCheckIn} className="btn-green">Check-in</button>
                    <button onClick={handleCheckOut} className="btn-red">Check-out</button>
                </div>

                {lastWork && (
                    <div className="result-box">
                        <h3>Resumo do dia</h3>
                        <p>Tempo trabalhado: <strong>{lastWork.formattedDuration}</strong></p>
                    </div>
                )}

                <div style={{ marginTop: '20px' }}>
                    <button onClick={() => navigate('/admin')} className="btn-outline">Ver Registros (Admin)</button>
                    <button onClick={() => { localStorage.removeItem('user'); navigate('/'); }} className="btn-text">Sair</button>
                </div>
            </div>
        </div>
    );
}

export default Dashboard;
