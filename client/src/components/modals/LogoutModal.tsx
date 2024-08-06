import ModalLayout from "../layouts/ModalLayout";
import { Box, Button, Select, Text, Textarea, TextInput } from "@mantine/core";
import { useState } from "react";
import { notifications } from "@mantine/notifications";

export default function LogoutModal() {
  const [loading, setLoading] = useState(false);
  const handleSave = () => {
    setLoading(true);
    setTimeout(() => {
      setLoading(false);
      notifications.show({
        title: "Comment Added",
        message: "The comment has been added successfully",
        color: "teal",
      });
    }, 2000);
  };
  return (
    <ModalLayout
      className="pt-5 absolute h-[200px] bg-black w-full"
      open={true}
      onClose={() => console.log("ji")}
    >
      <p>Logout</p>
    </ModalLayout>
  );
}
