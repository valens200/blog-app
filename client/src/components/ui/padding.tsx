export const Padding: React.FC<{
  children: React.ReactNode;
  className?: string;
}> = ({ children, className }) => {
  return (
    <div className={`md:px-40 sm:px-10 px-6 ${className}`}>{children}</div>
  );
};
