'use client';

import { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import Image from 'next/image';
import Button from '@/components/Button';

export default function Home() {
  const [image, setImage] = useState<File | null>(null);
  const [preview, setPreview] = useState<string>('');

  const handleChangeFile = (e: ChangeEvent<HTMLInputElement>) => {
    const file = e.target?.files?.[0];
    if (file) {
      setImage(file);
      const previewSrc = URL.createObjectURL(file);
      setPreview(previewSrc);
    }
  };

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    if (image) {
      const formData = new FormData();
      formData.append('image', image);
      const url = process.env.NEXT_PUBLIC_SERVER_URL + '/post/image';
      await fetch(url, {
        method: 'POST',
        credentials: 'include',
        body: formData,
      });
    }
  };

  useEffect(() => {
    return () => {
      if (preview) {
        URL.revokeObjectURL(preview);
      }
    };
  }, []);

  return (
    <form
      onSubmit={handleSubmit}
      className="flex flex-col gap-2 rounded-md bg-foreground p-4 shadow-md"
    >
      {preview && (
        <Image src={preview} width={200} height={200} alt={'image preview'} />
      )}
      <input
        type="file"
        onChange={handleChangeFile}
        accept="image/*"
        name="image"
      />
      <Button onClick={handleSubmit}>파일전송</Button>
    </form>
  );
}
