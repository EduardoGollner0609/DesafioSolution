import './styles.css';
import githubIcon from '../../assets/github-icon.svg';

export default function Header() {
    return (
        <header>
            <nav className="container">
                <h1>Address Manager</h1>
                <div className="github-code-space">
                    <a href="https://github.com/EduardoGollner0609/DesafioSolution">
                        <img src={githubIcon} alt="Github Icon" />
                        <p>Ver código fonte</p>
                    </a>
                </div>
            </nav>
        </header>
    );
}