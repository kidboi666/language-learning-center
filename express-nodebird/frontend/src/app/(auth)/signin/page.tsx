import AuthForm from '../_components/auth-form';
import { signInAction } from '@/app/actions/auth/auth-action';

export default function Page() {
  return <AuthForm action={signInAction} />;
}
