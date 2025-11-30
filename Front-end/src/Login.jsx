import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from './services/api';
import logo from './assets/logo.png';

function Login() {
    const [email, setEmail] = useState('admin@gmail.com');
    const [senha, setSenha] = useState('string');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await api.post('/auth/login', { email, senha });
            if (response.status === 200) {
                localStorage.setItem('user', JSON.stringify(response.data));
                navigate('/dashboard');
            }
        } catch (error) {
            alert('Erro ao fazer login: ' + (error.response?.data || error.message));
        }
    };

    return (
        <div className="container">
            <div className="card">
                <img src={logo} alt="Logo" className="logo" />
                <h1>Login</h1>
                <form onSubmit={handleLogin}>
                    <div className="form-group">
                        <label>Email</label>
                        <input
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Senha</label>
                        <input
                            type="password"
                            value={senha}
                            onChange={(e) => setSenha(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit">Entrar</button>
                </form>
            </div>
        </div>
    );
}

export default Login;
