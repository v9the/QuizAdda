select * from Users
delete from Users where UserNumber=1
create table Challenges(ChallengeID int primary key identity,ChallengeDate Date,FromUserNo int references Users(UserNumber),ToUserNo int references Users(UserNumber),CategoryID int references Categories(CategoryID),StatusNo int)

select * from Challenges

delete from Challenges

drop table Challenges

Select C.ChallengeID,C.ChallengeDate,C.FromUserNo,FU.UserName as [FromUserName],C.ToUserNo,TU.UserName as [ToUserName],Cat.CategoryID,C.StatusNo 
from Challenges as [C],Users as [TU],Users as [FU],Categories as [Cat]
where C.CategoryID=Cat.CategoryID and C.FromUserNo=FU.UserNumber and C.ToUserNo=TU.UserNumber and C.ToUserNo=5 and C.StatusNo=0


select * from Categories
select * from Questions
select * from Options
drop table Options
drop table Questions
drop table Categories
select * from Questions where CategoryID=4

create table AttemptedQuestions(SerialNumber int primary key identity, QuestionID int references Questions(QuestionID),OptionID int references Options(OptionID),UserNumber int references Users(UserNumber),ChallengeID int references Challenges(ChallengeID))

select * from challenges

select * from AttemptedQuestions
delete from AttemptedQuestions

select Q.QuestionText, AQ.OptionID ,O.OptionText, (Select OptionText from Options where QuestionID=AQ.QuestionID and IsAnswer=1) as [CorrectOptionText] , 
(select OptionID from Options where QuestionID=AQ.QuestionID and IsAnswer=1) as [CorrectOptionID] from Questions as [Q], AttemptedQuestions as[AQ],Options as [O]
where AQ.ChallengeID=53 and AQ.UserNumber=2 and Q.QuestionID=AQ.QuestionID and O.OptionID=AQ.OptionID

 
create table Categories(CategoryID int primary key identity,CategoryName varchar(20))

insert into Categories values('General Knowledge')

insert into Categories values('Science')

insert into Categories values('Sports')

insert into Categories values('Religious/Mythical')
delete from Categories where CategoryID=5

select * from Questions where CategoryID=2

update Challenges set StatusNo=0 where ChallengeID=13
 
create table Questions(QuestionID int primary key identity,QuestionText varchar(100),CategoryID int references Categories(CategoryID))

create table Options(OptionID int primary key identity, OptionText varchar(40),IsAnswer varchar(5),QuestionID int references Questions(QuestionID))


insert into Questions values('In Hindu Mythology , who is the God of Death ? ',4)
insert into Questions values('What are the gateways to Buddhist Stupas called? ',4)
insert into Questions values('Name the holy book of Parsis ?',4)
insert into Questions values('Who is the Goddess of speech,wisdom and learning ?',4)
insert into Questions values('Who was the first Sikh of Guru Nanak ?  ',4)
insert into Questions values('Christianity was first introduced in India at ?',4)
insert into Questions values('How many Sikh Gurus where there ?',4)
insert into Questions values('The Dilawara Temples at Mount Abu are dedicated to ?',4)
insert into Questions values('What is the Sikh Flag called ?',4)
insert into Questions values('Who delivered the message of Ram;s victory over Ravan to Sita ?',4)
insert into Questions values('In Mythology , which goddess keeps her tongue fully streched out ? ',4)
insert into Questions values('According to Mythology , what was the crime committed by Ganesha to get beheaded ? ',4)
insert into Questions values('Kumbh Karna sleeps for how many months ?',4)
insert into Questions values('Among Pandavas who was the youngest ?',4)
insert into Questions values('In which game Shakuni specialised ?',4)

insert into Options values('Indra',0,1)
insert into Options values('Saraswati',0,1)
insert into Options values('Yama',1,1)
insert into Options values('Brahma',0,1)
insert into Options values('Toranas',1,2)
insert into Options values('Ajantas',0,2)
insert into Options values('Jatakas',0,2)
insert into Options values('Pandavas',0,2)
insert into Options values('Zend Avesta',1,3)
insert into Options values('Bible',0,3)
insert into Options values('Kuran',0,3)
insert into Options values('Guru Granth Sahib',0,3)
insert into Options values('Shasti',0,4)
insert into Options values('Saraswati',1,4)
insert into Options values('Satyanarayana',0,4)
insert into Options values('Savitar',0,4)
insert into Options values('Bhai Mardana',0,5)
insert into Options values('Guru Angad',0,5)
insert into Options values('Bebe Nanaki',1,5)
insert into Options values('Baba Deep Singh',0,5)
insert into Options values('Chennai Coast',0,6)
insert into Options values('Gujrat Coast',0,6)
insert into Options values('Mumbai Coast',0,6)
insert into Options values('Malabar Coast',1,6)
insert into Options values('9',0,7)
insert into Options values('5',0,7)
insert into Options values('10',1,7)
insert into Options values('13',0,7)
insert into Options values('Bahubali',0,8)
insert into Options values('Mahavira',0,8)
insert into Options values('Adinath',1,8)
insert into Options values('Gautama Buddha',0,8)
insert into Options values('Gurudwara',0,9)
insert into Options values('Kirpan',0,9)
insert into Options values('Khanda',0,9)
insert into Options values('Nishan Sahib',1,9)
insert into Options values('Neela',0,10)
insert into Options values('Hanuman',1,10)
insert into Options values('Angad',0,10)
insert into Options values('Jambavan',0,10)
insert into Options values('Durga',0,11)
insert into Options values('Lakshmi',0,11)
insert into Options values('Sarawati',0,11)
insert into Options values('Kali',1,11)
insert into Options values('Disobidience to mother',0,12)
insert into Options values('Blocking the entrance for Shiva',1,12)
insert into Options values('Violating moral code',0,12)
insert into Options values('Quarrelling with brother Karthikeya',0,12)
insert into Options values('6',1,13)
insert into Options values('7',0,13)
insert into Options values('5',0,13)
insert into Options values('8',0,13)
insert into Options values('Nakul',0,14)
insert into Options values('Shadeva',1,14)
insert into Options values('Arjuna',0,14)
insert into Options values('Bhima',0,14)
insert into Options values('Chess',0,15)
insert into Options values('Dice Throwing',1,15)
insert into Options values('Playing cards',0,15)
insert into Options values('Sword fighting',0,15)










insert into Questions values('What is the national sport in Japan ? ',3)
insert into Questions values('For how many minutes is a rugby match played ?',3)
insert into Questions values('In which country were the first Olympis Games held ?',3)
insert into Questions values('How many players are there on each side of the net in beach volleyball ?',3)
insert into Questions values('What should you do in swordplay when you break  your saber ?',3)
insert into Questions values('How many matches did Mohammad Ali lose in his career ?',3)
insert into Questions values('How long is Olympic Swimming Pool ?',3)
insert into Questions values('At what number does one begin when playing darts ? ',3)
insert into Questions values('What is a yoga posture called ?',3)
insert into Questions values('Which popular fitness method was invented by  a German ?',3)
insert into Questions values('Which was the first non test playing country to beat India in an International match ?',3)
insert into Questions values('Ricky Ponting is also known as what ?',3)
insert into Questions values('India won its first Olympic hockey gold in ?',3)
insert into Questions values('Who among the following is not associated with billiards in India ?',3)
insert into Questions values('Who was the first ODI captain for India ?',3)


insert into Options values('Hockey',0,16)
insert into Options values('Sumo Wrestling',1,16)
insert into Options values('Judo',0,16)
insert into Options values('Kung Fu',0,16)
insert into Options values('60',0,17)
insert into Options values('50',0,17)
insert into Options values('80',1,17)
insert into Options values('75',0,17)
insert into Options values('Rio',0,18)
insert into Options values('India',0,18)
insert into Options values('Greece',0,18)
insert into Options values('Barcelona',0,18)
insert into Options values('3',0,19)
insert into Options values('1',0,19)
insert into Options values('4',0,19)
insert into Options values('2',1,19)
insert into Options values('Replace it',1,20)
insert into Options values('Continue playing',0,20)
insert into Options values('Consider a tie',0,20)
insert into Options values('Play bare hands',0,20)
insert into Options values('5',0,21)
insert into Options values('4',0,21)
insert into Options values('8',0,21)
insert into Options values('1',1,21)
insert into Options values('60m',0,22)
insert into Options values('50m',1,22)
insert into Options values('100m',0,22)
insert into Options values('75m',0,22)
insert into Options values('601',0,23)
insert into Options values('701',0,23)
insert into Options values('501',1,23)
insert into Options values('1001',0,23)
insert into Options values('Pranayam',0,24)
insert into Options values('Asana',1,24)
insert into Options values('YogShivir',0,24)
insert into Options values('Kapaalbhaati',0,24)
insert into Options values('Push ups',0,25)
insert into Options values('Leg Press',0,25)
insert into Options values('Pilates',1,25)
insert into Options values('Aerobics',0,25)
insert into Options values('Canada',0,26)
insert into Options values('Sri Lanka',1,26)
insert into Options values('Zimbabwe',0,26)
insert into Options values('East Africa',0,26)
insert into Options values('The Rickster',0,27)
insert into Options values('Ponts',0,27)
insert into Options values('Ponter',0,27)
insert into Options values('Punter',1,27)
insert into Options values('1928',1,28)
insert into Options values('1932',0,28)
insert into Options values('1936',0,28)
insert into Options values('1948',0,28)
insert into Options values('Subhash Aggarwal',0,29)
insert into Options values('Ashok Shandilya',0,29)
insert into Options values('Manoj Kothari',0,29)
insert into Options values('Mihir Sen',1,29)
insert into Options values('Bishan Singh Bedi',0,30)
insert into Options values('Nawab Pataudi',0,30)
insert into Options values('Ajit Wadekar',1,30)
insert into Options values('Vinoo Mankad',0,30)








insert into Questions values('Brass gets discoloured in air because of the  presence of which of the following gases in air ?',2)
insert into Questions values('Which of the following is a non - metal that remains liquid at room temperature ? ',2)
insert into Questions values('Chorophyll is a naturally occurring chelate compund in which central metal is ?',2)
insert into Questions values('Which of the following is used in graphite ? ',2)
insert into Questions values('Which of the following metals forms an amalgam with other metals ?',2)
insert into Questions values('What is the unit for measuring the amplitude of a sound ? ',2)
insert into Questions values('One Fathom is equal to ? ',2)
insert into Questions values('One kilometer is equal to how many miles ?',2)
insert into Questions values('Which of the following is used as moderator in a nulclear reactor',2)
insert into Questions values('Atoms are composed of ?',2)
insert into Questions values('Detergents used for cleaning clothes and utensils contain ?',2)
insert into Questions values('Which blood group is the univeral blood donor ?',2)
insert into Questions values('How many basic tastes can humans sense ?',2)
insert into Questions values('The first country to send man to the Moon was ?',2)
insert into Questions values('First scientific satellite of India was ?',2)


insert into Options values('Oxygen',0,31)
insert into Options values('Hydrogen Sulphide',1,31)
insert into Options values('Carbon Dioxide',0,31)
insert into Options values('Nitrogen',0,31)
insert into Options values('Phosphorus',0,32)
insert into Options values('Bromine',1,32)
insert into Options values('Chlorine',0,32)
insert into Options values('Helium',0,32)
insert into Options values('Copper',0,33)
insert into Options values('Iron',0,33)
insert into Options values('Calcium',0,33)
insert into Options values('Magnesium',1,33)
insert into Options values('Graphite',1,34)
insert into Options values('Silicon',0,34)
insert into Options values('Charcoal',0,34)
insert into Options values('Phosphorus',0,34)
insert into Options values('Tin',0,35)
insert into Options values('Mercury',1,35)
insert into Options values('Lead',0,35)
insert into Options values('Zinc',0,35)
insert into Options values('Decibel',1,36)
insert into Options values('Coulomb',0,36)
insert into Options values('Hum',0,36)
insert into Options values('Cycles',0,36)
insert into Options values('6 metres',0,37)
insert into Options values('6 feet',1,37)
insert into Options values('60 feet',0,37)
insert into Options values('100 cms',0,37)
insert into Options values('0.84',0,38)
insert into Options values('0.5',0,38)
insert into Options values('0.62',1,38)
insert into Options values('1.6',0,38)
insert into Options values('Ordinary Water',0,39)
insert into Options values('Radium',0,39)
insert into Options values('Thorium',0,39)
insert into Options values('Graphite',1,39)
insert into Options values('electrons and protons',0,40)
insert into Options values('electrons and nuclei',1,40)
insert into Options values('electrons only',0,40)
insert into Options values('protons only',0,40)
insert into Options values('Biocarbonates',0,41)
insert into Options values('Bismuthates',0,41)
insert into Options values('Nitrates',0,41)
insert into Options values('Sulphonates',1,41)
insert into Options values('O',1,42)
insert into Options values('AB',0,42)
insert into Options values('A',0,42)
insert into Options values('B',0,42)
insert into Options values('1',0,43)
insert into Options values('4',0,43)
insert into Options values('5',1,43)
insert into Options values('6',0,43)
insert into Options values('India',0,44)
insert into Options values('USA',1,44)
insert into Options values('Japan',0,44)
insert into Options values('China',0,44)
insert into Options values('Agni',0,45)
insert into Options values('Akaash',0,45)
insert into Options values('Aryabhatta',1,45)
insert into Options values('Vayu',0,45)















insert into Questions values('Who wrote the constitution of India ?',1)
insert into Questions values('Which of the current states in India was never ruled by Britishers ?',1)
insert into Questions values('Entomology is the science that studies ',1)
insert into Questions values('For which of the following disciplines is Nobel Prize awarded?',1)
insert into Questions values('Hitler party which came into power in 1933 is known as ?',1)
insert into Questions values('Grand Terminal Central,Park Avenue, New York is the largest what in the world ?',1)
insert into Questions values('Garampani sanctuary is located at ? ',1)
insert into Questions values('FFC stands for ?',1)
insert into Questions values('Galileo was an Italian astronomer who : ',1)
insert into Questions values('Exposure to sunlight helps a person improve his health because ?',1)
insert into Questions values('First China War was between ?',1)
insert into Questions values('Red Cross Day is celebraated on ?',1)
insert into Questions values('Friction can be reduced by changing from : ',1)
insert into Questions values('The ozone layer restricts ?',1)
insert into Questions values('For seeing objects at the surface of water from an underwater submarine what is used ?',1)


insert into Options values('B.R. Ambedkar',1,46)
insert into Options values('V.K. Saini',0,46)
insert into Options values('Mahatama Gandhi',0,46)
insert into Options values('Jawaharlal Nehru',0,46)
insert into Options values('Delhi',0,47)
insert into Options values('Punjab',0,47)
insert into Options values('Goa',1,47)
insert into Options values('Maharashtra',0,47)
insert into Options values('Insects',1,48)
insert into Options values('Behaviour of human being',0,48)
insert into Options values('Formation of rocks',0,48)
insert into Options values('Origin and history of Technical terms',0,48)
insert into Options values('Physics and Chemistry',0,49)
insert into Options values('Physiology or Medicine',0,49)
insert into Options values('Literature,Peace and Economics',0,49)
insert into Options values('All the above',1,49)
insert into Options values('Labour Party',0,50)
insert into Options values('Ku-Klux-Klan',0,50)
insert into Options values('Nazi Party',1,50)
insert into Options values('Democratic Party',0,50)
insert into Options values('Largest Railway Station',1,51)
insert into Options values('Highest Railway Station',0,51)
insert into Options values('Longest Railway Station',0,51)
insert into Options values('None of the above',0,51)
insert into Options values('Diphu, Assam',1,52)
insert into Options values('Kohima,Nagaland',0,52)
insert into Options values('Gangtok, Sikkim',0,52)
insert into Options values('Junagarh, Gujrat',0,52)
insert into Options values('Foreign Finance Corporation',0,53)
insert into Options values('Film Finance Corporation',1,53)
insert into Options values('Federation of Football Council',0,53)
insert into Options values('None of the above',0,53)
insert into Options values('Developed the Telescope',0,54)
insert into Options values('Discovered four satellites of Jupitar',0,54)
insert into Options values('Discovered regularity in the pendulum',0,54)
insert into Options values('All of the above',1,54)
insert into Options values('resistence power increases',0,55)
insert into Options values('the infrared kills bacteria',0,55)
insert into Options values('pigment cells of skin get stimulated',0,55)
insert into Options values('uv rays convert skin oil to Vit D',1,55)
insert into Options values('China and Egypt',0,56)
insert into Options values('China and Greek',0,56)
insert into Options values('China and Britain',1,56)
insert into Options values('China and France',0,56)
insert into Options values('May 8',1,57)
insert into Options values('May 18',1,57)
insert into Options values('June  8',0,57)
insert into Options values('June 18',0,57)
insert into Options values('rolling o sliding',0,58)
insert into Options values('sliding to rolling',1,58)
insert into Options values('dynamic to static',0,58)
insert into Options values('potential energy to kinetic energy',0,58)
insert into Options values('Visible Light',0,59)
insert into Options values('Infrared radiations',0,59)
insert into Options values('X-rays and gamma-rays',0,59)
insert into Options values('Ultraviolet rays',1,59)
insert into Options values('kaleidoscope',0,60)
insert into Options values('periscope',1,60)
insert into Options values('spectroscope',0,60)
insert into Options values('telscope',0,60)
