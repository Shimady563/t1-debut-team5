import AuthForm from '@/modules/AuthForm/components/AuthForm/AuthForm';
import './AuthPage.scss';
import TechnologiesChipper from '@/modules/TechnologiesChipper/components/TechnologiesChipper';

const AuthPage = () => {
  return (
    <div className="auth-page">
      <AuthForm />
      {/* <TechnologiesChipper /> */}
    </div>
  );
};

export default AuthPage;
