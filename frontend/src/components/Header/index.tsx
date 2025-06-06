import './styles.css';
import githubIcon from '../../assets/github-icon.svg';
import { Link } from 'react-router-dom';

export default function Header() {
    return (
        <header>
            <nav className="container">
                <Link to="/"><h1>User Cep Manager</h1></Link>
                <div className="github-code-space">
                    <a href="https://github.com/EduardoGollner0609/UserCepManager">
                        <img src={githubIcon} alt="Github Icon" />
                        <p>Ver código fonte</p>
                    </a>
                </div>
            </nav>
        </header>
    );
}