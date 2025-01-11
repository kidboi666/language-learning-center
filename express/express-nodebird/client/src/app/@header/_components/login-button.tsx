import Button from '@/components/Button';
import Link from 'next/link';

export default function LoginButton() {
  return (
    <Link href="/auth/signin">
      <Button>로그인 하러가기</Button>
    </Link>
  );
}
