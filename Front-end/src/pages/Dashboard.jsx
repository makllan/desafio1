import { useState, useEffect } from 'react';
import api from '../services/api';
import { useNavigate } from 'react-router-dom';

function Dashboard() {
  const [user, setUser] = useState(null);
  const [workedTime, setWorkedTime] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const storedUser = localStorage.getItem('user');
    if (!storedUser) {
      navigate('/');
      return;
    }
    setUser(JSON.parse(storedUser));
  }, [navigate]);

  const handleCheckIn = async () => {
    try {
      await api.post(`/work/checkin/${user.id}`);
      alert('Check-in realizado!');
    } catch (error) {
      alert('Erro no check-in');
    }
  };

  const handleCheckOut = async () => {
    try {
      const response = await api.post(`/work/checkout/${user.id}`);
      setWorkedTime(response.data.formattedDuration);
      alert('Check-out realizado!');
    } catch (error) {
      alert('Erro no check-out');
    }
  };

  if (!user) return null;

  return (
    <div className="dashboard-container">
      <h1>Bem-vindo, {user.name}</h1>
      <div className="actions">
        <button onClick={handleCheckIn} className="btn-checkin">Check-in</button>
        <button onClick={handleCheckOut} className="btn-checkout">Check-out</button>
      </div>
      {workedTime && <p className="worked-time">Tempo trabalhado hoje: {workedTime}</p>}
      <button onClick={() => navigate('/admin')} className="btn-admin">Ir para Admin</button>
    </div>
  );
}

export default Dashboard;
