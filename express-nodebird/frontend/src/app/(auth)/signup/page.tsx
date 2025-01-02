import { signUpAction } from '@/app/actions/auth/auth-action';
import AuthForm from '@/app/(auth)/_components/auth-form';

export default function Page() {
  return <AuthForm action={signUpAction} />;
}
