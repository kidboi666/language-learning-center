import Button from '@/components/Button';

export default function OauthKakaoButton() {
  return (
    <a href="http://localhost:8001/oauth/kakao">
      <Button className="bg-yellow-400">카카오 로그인</Button>
    </a>
  );
}
