package fun.forAlice.AlicePi.core.entity.serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Collection;

import com.pi4j.io.serial.Baud;
import com.pi4j.io.serial.DataBits;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialConfig;
import com.pi4j.io.serial.SerialDataEventListener;
import com.pi4j.io.serial.StopBits;

public class SerialDemo implements Serial{
	public void	addListener(SerialDataEventListener... listener) {
		
	}
	public int	available() {
		return -1;
	}
	public void	close() {
		
	}
	public void	discardAll() {
		
	}
	public void	discardInput() {
		
	}
	public void	discardOutput() {
		
	}
	public void	flush() {
		
	}
	public boolean	getCD() {
		return false;
	}
	public boolean	getCTS() {
		return false;
	}
	public boolean	getDSR() {
		return false;
	}
	public boolean	getDTR() {
		return false;
	}
	public int	getFileDescriptor() {
		return 0;
	}
	public InputStream	getInputStream() {
		return null;

	}
	public OutputStream	getOutputStream() {
		return null;
	}
	public boolean	getRI() {
		return false;

	}
	public boolean	getRTS() {
		return false;

	}
	public boolean	isBufferingDataReceived(){
		return false;

	}
	public boolean	isClosed(){
		return false;

	}
	public boolean	isOpen(){
		return false;
	}
	public void	open(SerialConfig serialConfig){
		
	}
	public void	open(String device, Baud baud, DataBits dataBits, Parity parity, StopBits stopBits, FlowControl flowControl){
		
	}
	public void	open(String device, int baud){
		
	}
	public void	open(String device, int baud, int dataBits, int parity, int stopBits, int flowControl){
		
	}
	public byte[]	read(){
		return new byte[0];
	}
	public byte[]	read(int length){
		return new byte[0];
	}
	public void	removeListener(SerialDataEventListener... listener){
		
	}
	public void	sendBreak(){
		
	}
	public void	sendBreak(int duration){
		
	}
	public void	setBreak(boolean enabled){
		
	}
	public void	setBufferingDataReceived(boolean enabled){
		
	}
	public void	setDTR(boolean enabled){
		
	}
	public void	setRTS(boolean enabled){
		
	}
	public void	write(byte[] data, int offset, int length){
		
	}
	@Override
	public void discardData() throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void read(ByteBuffer buffer) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void read(int length, ByteBuffer buffer) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void read(OutputStream stream) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void read(int length, OutputStream stream) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void read(Collection<ByteBuffer> collection) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void read(int length, Collection<ByteBuffer> collection) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public CharBuffer read(Charset charset) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CharBuffer read(int length, Charset charset) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void read(Charset charset, Writer writer) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void read(int length, Charset charset, Writer writer) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void write(byte... data) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void write(byte[]... data) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void write(ByteBuffer... data) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void write(InputStream input) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void write(Charset charset, char[] data, int offset, int length) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void write(Charset charset, char... data) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void write(char... data) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void write(Charset charset, CharBuffer... data) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void write(CharBuffer... data) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void write(Charset charset, CharSequence... data) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void write(CharSequence... data) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void write(Charset charset, Collection<? extends CharSequence> data)
			throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void write(Collection<? extends CharSequence> data) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void writeln(Charset charset, CharSequence... data) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void writeln(CharSequence... data) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void writeln(Charset charset, Collection<? extends CharSequence> data)
			throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void writeln(Collection<? extends CharSequence> data) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}
}
